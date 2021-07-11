
package com.omen.learning.sample.valve.handler;


import com.omen.learning.sample.valve.Valve;

/**
 * @author :
 * @since : 2020/1/16, Thu
 **/
public interface Handler extends Valve {
    /**
     * @param param
     */
    void handle(Object param);

    @Override
    default void invoke(Object param) {
        handle(param);
    }
}
