package com.omen.learning.sample.controller;

import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suxing.zhang
 * @date 2021/7/11 14:11
 **/
@RestController
@RequestMapping("/api/v1")
public class PipelineController {

    /**
     * 接收B端发货单接口
     * 当前业务1.自由JIT发货
     */
    @PostMapping("/dispatch")
    public CommonDataResponse<String> acceptVendorDispatchBill() {
        return CommonDataResponse.success("success");
    }

}
