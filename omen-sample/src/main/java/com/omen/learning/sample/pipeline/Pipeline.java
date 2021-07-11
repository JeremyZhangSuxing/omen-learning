
package com.omen.learning.sample.pipeline;


import com.omen.learning.sample.valve.Cleanup;
import com.omen.learning.sample.valve.Rollback;
import com.omen.learning.sample.valve.Valve;
import com.sun.prism.impl.BaseContext;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : suxing.zhang
 * @since : 2020/1/16, Thu
 **/
public class Pipeline {
    private Queue<Valve> queue = new LinkedList<>();

    public void add(Valve valve) {
        this.queue.add(valve);
    }

    public void doFlowing(BaseContext context) {
        Deque<Cleanup> cleanupDeque = new LinkedList<>();
        Deque<Rollback> rollbackDeque = new LinkedList<>();
        try {
            for (Valve valve : queue) {
                if (valve instanceof Rollback) {
                    rollbackDeque.addFirst((Rollback) valve);
                }
                if (valve instanceof Cleanup) {
                    cleanupDeque.addFirst((Cleanup) valve);
                }
                valve.invoke(context);
                //中断直接返回
//                if (context.isInterruptSignal()) {
//                    break;
//                }
            }
        } catch (Throwable t) {
            for (Rollback r : rollbackDeque) {
                r.rollback(context, t);
            }
            throw t;
        } finally {
            for (Cleanup cleanup : cleanupDeque) {
                cleanup.cleanup(context);
            }
        }
    }
}
