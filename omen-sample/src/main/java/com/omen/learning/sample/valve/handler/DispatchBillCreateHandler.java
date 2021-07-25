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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @author suxing.zhang
 * @date 2021/7/18 15:21
 **/
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
        dispatchBillInfo.setOrderCreateDate(dispatch.getBillDate());
        dispatchBillInfo.setUpdatedTime(LocalDateTime.now());
        dispatchBillInfo.setCreatedBy("system");
        dispatchBillInfo.setCreatedTime(LocalDateTime.now());
        List<DispatchBillDetail> items = new ArrayList<>(context.getItems().size());
        for (DispatchItem item : context.getItems()) {
            DispatchBillDetail detail = new DispatchBillDetail();
            BeanUtils.copyProperties(item, detail);
            detail.setCreatedTime(LocalDateTime.now());
            detail.setUpdatedTime(LocalDateTime.now());
            detail.setCreatedBy("system");
            items.add(detail);
        }
        dispatchRepository.saveDispatch(dispatchBillInfo, items);
    }
}