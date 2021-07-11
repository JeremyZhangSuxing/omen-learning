
package com.omen.learning.sample.factory;

import com.omen.learning.sample.pipeline.Pipeline;
import com.omen.learning.sample.valve.Valve;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author : suxing.zhang
 * @since : 2020/1/17, Fri
 **/
public abstract class AbstractPipeFactory implements IBillFactory {
    @Autowired
    private Map<String, Valve> valves;

    protected <T extends Pipeline> T buildBillPipe(Supplier<T> supplier, String... valveNames) {
        T pipeline = supplier.get();
        for (String valveName : valveNames) {
            Valve valve = valves.get(valveName);
            if (Objects.isNull(valve)) {
                throw new RuntimeException("Valve " + valveName + " was not found");
            }
            pipeline.add(valve);
        }
        return pipeline;
    }
}
