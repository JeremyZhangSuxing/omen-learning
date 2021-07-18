
package com.omen.learning.sample.factory;

import com.omen.learning.sample.pipeline.Pipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : knight-tes
 * @since : 2020/1/17, Fri
 **/
@Component
@RequiredArgsConstructor
public class PipelineBuilder {
    private final List<IBillFactory> billFactories;

    public Pipeline buildPipeline(BeanMatchCondition condition) {
        for (IBillFactory billFactory : billFactories) {
            if (billFactory.matchByCondition(condition)) {
                return billFactory.build(condition);
            }
        }
        return null;
    }

}
