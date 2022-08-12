package com.omen.learning.sample.controller;

import com.omen.learning.common.entity.BillDTO;
import com.omen.learning.sample.mybatis.mapper.CmOrderMapper;
import com.omen.learning.sample.support.OrderCacheRefreshEvent;
import com.weweibuy.framework.common.core.model.dto.CommonCodeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Knight
 * @date : 2021/4/23 5:00 下午
 */
@RestController
@RequestMapping("/api/event")
public class EventController {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private CmOrderMapper cmOrderMapper;
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @PutMapping
    public CommonCodeResponse pushEvent() {
        applicationContext.publishEvent(new OrderCacheRefreshEvent("order"));
        return CommonCodeResponse.success();
    }

    @PutMapping("async")
    public CommonCodeResponse pushEventAsync(HttpServletRequest httpServletRequest) {
        applicationEventPublisher.publishEvent(BillDTO.buildWithReq("JITX", httpServletRequest));
        return CommonCodeResponse.success();
    }

    @PutMapping("/transaction")
    public CommonCodeResponse transactionEvent() {
        applicationEventPublisher.publishEvent(BillDTO.buildPro("B2C", null));
        return CommonCodeResponse.success();
    }
}
