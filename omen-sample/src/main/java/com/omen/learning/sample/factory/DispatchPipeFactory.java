
package com.omen.learning.sample.factory;


import com.omen.learning.sample.pipeline.DispatchPipeline;
import com.omen.learning.sample.pipeline.Pipeline;
import org.springframework.stereotype.Component;


/**
 * @author : suxing.zhang
 * @since : 2020/4/16, Thu
 **/
@Component
public class DispatchPipeFactory extends AbstractPipeFactory {
    @Override
    public Pipeline build(Object param) {
        return buildBillPipe(DispatchPipeline::new,
                "dispatchIdempotentHandler",
//                "bwJitValidator",
//                "dispatchBillCreatePreparation",
                "dispatchBillCreateHandler"
//                "dispatchBillTaskHandler"
        );
    }

    @Override
    public boolean matchByCondition(BeanMatchCondition condition) {
        return true;
    }
}
