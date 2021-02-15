package com.omen.learning.sample.controller;

import com.omen.learning.sample.mybatis.po.CmOrder;
import com.omen.learning.sample.test.TestService;
import com.weweibuy.framework.common.core.model.dto.CommonCodeResponse;
import com.weweibuy.framework.common.core.model.dto.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author suxing.zhang
 * @date 2021/1/26 22:00
 **/
@Slf4j
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "order", cacheManager = "caffeineCacheManager")
public class CacheController {
    private final TestService testService;

    @GetMapping
    @Cacheable(key = "#id")
    public CommonDataResponse<CmOrder> query(@RequestParam Long id) {
        log.info("查询数据from db");
        return CommonDataResponse.success(testService.listOrders(id));
    }

    @PutMapping
    @CachePut(key = "#cmOrder.id")
    public CommonDataResponse<CmOrder> update(@RequestBody CmOrder cmOrder) {
        log.info("查询数据from db");
        return CommonDataResponse.success(testService.updateOrder(cmOrder));
    }

    @DeleteMapping
    @CacheEvict(key = "#id")
    public CommonCodeResponse delete(@RequestParam Long id) {
        log.info("清除缓存，数据库数据状态变更");
        return CommonCodeResponse.success();
    }

    @DeleteMapping("/all")
    @CacheEvict(beforeInvocation = true, allEntries = true)
    public CommonCodeResponse deleteAll(@RequestParam Long id) {
        log.info("清除缓存，数据库数据状态变更");
        return CommonCodeResponse.success();
    }


    @PostMapping
    @CachePut(key = "#cmOrder.id")
    public CommonDataResponse<CmOrder> save(@RequestBody CmOrder cmOrder) {
        log.info("数据新增并刷新缓存");
        return CommonDataResponse.success(testService.saveOrder(cmOrder));
    }

    @Caching(
            cacheable = {@Cacheable(cacheNames = "order", key = "cmOrder.id"), @Cacheable(cacheNames = "order", key = "cmOrder.orderNo")}
    )
    public CommonDataResponse<CmOrder> select(CmOrder cmOrder) {
        log.info("--数据库查询--");
        return CommonDataResponse.success(testService.saveOrder(cmOrder));
    }
}
