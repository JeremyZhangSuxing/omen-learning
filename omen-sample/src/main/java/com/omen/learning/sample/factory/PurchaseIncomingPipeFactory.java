
package com.omen.learning.sample.factory;


import com.omen.learning.sample.pipeline.Pipeline;
//import com.omen.learning.sample.pipeline.ReceivePipeline;
import org.springframework.stereotype.Component;

/**
 * 采购到货单创建
 *
 * @author : suxing.zhang
 * @since : 2020/4/16, Thu
 **/
@Component
public class PurchaseIncomingPipeFactory extends AbstractPipeFactory {
    @Override
    public Pipeline build(Object param) {
        return null;
//        return buildBillPipe(ReceivePipeline::new,
//                "receiveIdempotentHandler",
//                "purchaseIncomingValidator",
//                "receiveBillCreatePreparation",
//                "receiveBillCreateHandler",
//                "receiveBillAddTaskHandler");
    }

    @Override
    public boolean matchByCondition(BeanMatchCondition condition) {
       return true;
    }
}
