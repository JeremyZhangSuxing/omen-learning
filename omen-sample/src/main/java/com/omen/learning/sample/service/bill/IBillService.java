package com.omen.learning.sample.service.bill;

import com.omen.learning.common.dto.DispatchRequest;
import com.omen.learning.sample.factory.BeanMatchCondition;
import com.omen.learning.sample.factory.PipelineBuilder;
import com.omen.learning.sample.pipeline.DispatchContext;
import com.omen.learning.sample.pipeline.DispatchPipeline;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 * @author suxing.zhang
 * @date 2021/7/18 15:21
 **/
@Component
@RequiredArgsConstructor
public class IBillService {
    private final PipelineBuilder pipelineBuilder;

    public CommonDataResponse<String> createDispatchBill(DispatchRequest dispatchRequest) {
        return runDisInPipeline(dispatchRequest, (DispatchPipeline) pipelineBuilder.buildPipeline(
                BeanMatchCondition.builder()
                        .bizType(dispatchRequest.getDispatch().getBizType())
                        .beanType(NumberUtils.INTEGER_ONE)
                        .build()));
    }

    private CommonDataResponse<String> runDisInPipeline(DispatchRequest dispatchRequest, DispatchPipeline dispatchPipeline) {
        DispatchContext context = new DispatchContext(dispatchRequest.getDispatch(), dispatchRequest.getItems());
        context.setResp(CommonDataResponse.success(""));
        dispatchPipeline.flows(context);
        return CommonDataResponse.success(context.getDispatch().getBillNo());
    }
}


