package com.omen.learning.sample.controller;

import com.omen.learning.common.dto.DispatchRequest;
import com.omen.learning.sample.service.bill.IBillService;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suxing.zhang
 * @date 2021/7/11 14:11
 **/
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PipelineController {
    private final IBillService iBillService;

    /**
     * 接收B端发货单接口
     * 当前业务1.自由JIT发货
     */
    @PostMapping("/dispatch")
    public CommonDataResponse<String> acceptVendorDispatchBill(DispatchRequest dispatchRequest) {
        return iBillService.createDispatchBill(dispatchRequest);
    }

}
