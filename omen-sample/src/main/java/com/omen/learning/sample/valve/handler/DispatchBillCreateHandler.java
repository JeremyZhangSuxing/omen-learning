package com.omen.learning.sample.valve.handler;

import com.omen.learning.common.entity.Dispatch;
import com.omen.learning.common.entity.DispatchItem;
import com.omen.learning.sample.mybatis.po.DispatchBillDetail;
import com.omen.learning.sample.mybatis.po.DispatchBillInfo;
import com.omen.learning.sample.pipeline.DispatchContext;
import com.omen.learning.sample.repository.DispatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DispatchBillCreateHandler implements Handler {
    private final DispatchRepository dispatchRepository;

    @Override
    public void handle(Object param) {
        //prepare entity
        DispatchContext context = (DispatchContext) param;
        Dispatch dispatch = context.getDispatch();
        DispatchBillInfo dispatchBillInfo = new DispatchBillInfo();
        BeanUtils.copyProperties(dispatch, dispatchBillInfo);
        List<DispatchBillDetail> items = new ArrayList<>(context.getItems().size());
        for (DispatchItem item : context.getItems()) {
            DispatchBillDetail detail = new DispatchBillDetail();
            BeanUtils.copyProperties(item, detail);
            items.add(detail);
        }
        dispatchRepository.saveDispatch(dispatchBillInfo, items);
    }
}