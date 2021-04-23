package com.omen.learning.sample.controller;

import com.omen.learning.sample.support.OrderCacheRefreshEvent;
import com.weweibuy.framework.common.core.model.dto.CommonCodeResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : Knight
 * @date : 2021/4/23 5:00 下午
 */
@RestController
@RequestMapping("/api/event")
public class EventController {
    @Resource
    private ApplicationContext applicationContext;

    @PutMapping
    public CommonCodeResponse pushEvent() {
        applicationContext.publishEvent(new OrderCacheRefreshEvent("order"));
        return CommonCodeResponse.success();
    }
}
