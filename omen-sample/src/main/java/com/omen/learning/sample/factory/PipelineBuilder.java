
package com.omen.learning.sample.factory;

import com.omen.learning.sample.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author :
 * @since : 2020/1/17, Fri
 **/
@Component
public class PipelineBuilder {
    @Autowired
    private List<IBillFactory> billFactories;

    public Pipeline buildPipeline(BeanMatchCondition condition) {
        for (IBillFactory billFactory : billFactories) {
            if (billFactory.matchByCondition(condition)) {
                return billFactory.build(condition);
            }
        }
//        throw new ClientException("no pipeline for condition: " + condition);
        return null;
    }

}
