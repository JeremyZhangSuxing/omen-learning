package com.omen.learning.sample.controller;

import com.omen.learning.sample.mybatis.po.CmOrder;
import com.omen.learning.sample.test.TestService;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suxing.zhang
 * @date 2021/1/26 22:00
 **/
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@Cacheable(cacheNames = "order",cacheManager = "caffeineCacheManager")
public class CacheController {
    private final TestService testService;

    @GetMapping("/list")
    @Cacheable(key = "#id")
    public CommonDataResponse<List<CmOrder>> query(@RequestParam String id) {
        return CommonDataResponse.success(testService.listOrders());
    }


}
