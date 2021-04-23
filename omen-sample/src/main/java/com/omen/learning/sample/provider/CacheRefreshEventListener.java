package com.omen.learning.sample.provider;

import com.github.benmanes.caffeine.cache.Cache;
import com.omen.learning.sample.support.OrderCacheRefreshEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheRefreshEventListener implements ApplicationListener<OrderCacheRefreshEvent> {
    private final CacheManager cacheManager;

    /**
     * 监听事件 OrderCacheRefreshEvent
     * 目标缓存下的 key失效
     */
    @Override
    public void onApplicationEvent(OrderCacheRefreshEvent event) {
        log.info("CacheRefreshEventListener listened event and start to do something....");
        String cacheName = event.getCacheName();
        Cache<Object, Object> nativeCache = (Cache<Object, Object>) cacheManager.getCache(cacheName).getNativeCache();
        nativeCache.invalidateAll();
    }
}