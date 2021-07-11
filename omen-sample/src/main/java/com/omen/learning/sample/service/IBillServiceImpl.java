//
//package com.omen.learning.sample.service;
//
//
//import com.omen.learning.sample.context.DispatchContext;
//import com.omen.learning.sample.context.ReceiveContext;
//import com.omen.learning.sample.factory.BeanMatchCondition;
//import com.omen.learning.sample.factory.PipelineBuilder;
//import com.omen.learning.sample.pipeline.DispatchPipeline;
//import com.omen.learning.sample.pipeline.ReceivePipeline;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author : suxing.zhang
// * @since : 2020/1/17, Fri
// **/
//@Service
//public class IBillServiceImpl implements IBillService {
//    @Autowired
//    private PipelineBuilder pipelineBuilder;
//
//    @Override
//    public OdsResponseVo createDispatchBill(DispatchRequest dispatchRequest) {
//        return runDisInPipeline(dispatchRequest, (DispatchPipeline) pipelineBuilder.buildPipeline(
//                BeanMatchCondition.builder()
//                        .bizType(dispatchRequest.getDispatch().getBizType())
//                        .beanType(NumberUtils.INTEGER_ONE)
//                        .build()));
//    }
//
//    @Override
//    public OdsResponseVo createReceiveBill(ReceiveRequest receiveRequest) {
//        return runRecInPipeline(receiveRequest, (ReceivePipeline) pipelineBuilder.buildPipeline(
//                BeanMatchCondition.builder()
//                        .bizType(receiveRequest.getReceive().getBizType())
//                        .beanType(NumberUtils.INTEGER_ONE)
//                        .build()
//        ));
//    }
//
//    private OdsResponseVo runDisInPipeline(DispatchRequest dispatchRequest, DispatchPipeline dispatchPipeline) {
//        DispatchContext context = new DispatchContext(dispatchRequest.getDispatch(), dispatchRequest.getWarehouse(),
//                dispatchRequest.getSettlement(), dispatchRequest.getItems());
//        context.setResp(OdsResponseVo.success());
//        dispatchPipeline.flows(context);
//        return OdsResponseVo.buildOdesResponse(ApiErrorCode.SUCCESS,
//                CreateDispatch4CstResp.builder().dispatchNo(context.getDispatch().getBillNo()).build());
//    }
//
//    private OdsResponseVo runRecInPipeline(ReceiveRequest receiveRequest, ReceivePipeline receivePipeline) {
//        ReceiveContext context = new ReceiveContext(receiveRequest.getReceive(), receiveRequest.getWarehouse(),
//                receiveRequest.getSettlement(), receiveRequest.getItems());
//        context.setResp(OdsResponseVo.success());
//        receivePipeline.flows(context);
//        return context.getResp();
//    }
//}
