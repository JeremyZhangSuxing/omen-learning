package com.omen.learning.sample.valve.handler;

import com.omen.learning.common.entity.DispatchItem;
import com.omen.learning.common.enums.BillCommonTypeEnum;
import com.omen.learning.sample.pipeline.DispatchContext;
import com.omen.learning.sample.provider.BillNoManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author suxing.zhang
 * @date 2021/7/25 10:41
 **/
@Component
@RequiredArgsConstructor
public class DispatchBillCreatePreparation implements Handler {
    private final BillNoManager billNoManager;

    @Override
    public void handle(Object param) {
        if (param instanceof DispatchContext) {
            DispatchContext context = (DispatchContext) param;
            String orderNo = context.getDispatch().getOrderNo();
            String billNo = billNoManager.generateBillNo(orderNo, BillCommonTypeEnum.DISPATCH);
            context.getDispatch().setBillNo(billNo);
            for (DispatchItem item : context.getItems()) {
                item.setBillNo(billNo);
            }
        }
    }
}
