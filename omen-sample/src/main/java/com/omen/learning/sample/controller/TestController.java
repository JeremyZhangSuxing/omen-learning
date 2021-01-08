package com.omen.learning.sample.controller;

import com.omen.learning.sample.mybatis.po.CmOrder;
import com.omen.learning.sample.test.TestService;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Knight
 * @date : 2021/1/8 5:26 下午
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/orders")
    public CommonDataResponse<List<CmOrder>> queryList() {
        return CommonDataResponse.success(testService.listOrders());
    }
}
