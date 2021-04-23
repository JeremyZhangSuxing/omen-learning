package com.omen.learning.sample.support;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author : Knight
 * @date : 2021/4/23 4:54 下午
 */
@Getter
public class OrderCacheRefreshEvent extends ApplicationEvent {
    private final String cacheName;

    public OrderCacheRefreshEvent(String cacheName) {
        super(cacheName);
        this.cacheName = cacheName;
    }

}
