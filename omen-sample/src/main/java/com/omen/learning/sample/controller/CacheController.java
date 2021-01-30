package com.omen.learning.sample.controller;

import com.omen.learning.sample.mybatis.po.CmOrder;
import com.omen.learning.sample.test.TestService;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @author suxing.zhang
 * @date 2021/1/26 22:00
 **/
@Slf4j
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "order",cacheManager = "caffeineCacheManager")
public class CacheController {
    private final TestService testService;

    @GetMapping
    @Cacheable(key = "#id")
    public CommonDataResponse<CmOrder> query(@RequestParam Long id) {
        log.info("查询数据from db");
        return CommonDataResponse.success(testService.listOrders(id));
    }

    @PostMapping
    @CacheEvict(key = "#cmOrder.id")
    public CommonDataResponse<CmOrder> update(@RequestBody CmOrder cmOrder) {
        log.info("查询数据from db");
        return CommonDataResponse.success(testService.updateOrder(cmOrder));
    }
}
