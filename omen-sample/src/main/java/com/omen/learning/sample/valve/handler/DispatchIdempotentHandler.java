package com.omen.learning.sample.valve.handler;

import com.omen.learning.common.entity.Dispatch;
import com.omen.learning.sample.pipeline.DispatchContext;
import com.omen.learning.sample.valve.Cleanup;
import com.weweibuy.framework.common.core.exception.Exceptions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.UUID;

@Component
public class DispatchIdempotentHandler implements Handler, Cleanup {
    private static final String LOCK_KEY = "BILL_LOCK_";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${bill.idempotent.retry.times:3}")
    private int maxRetries;

    @Value("${bill.idempotent.key.timeout:10000}")
    private long keyTimeout;

    @Value("${bill.idempotent.sleep.time:1000}")
    private long sleepTime;

    @Override
    public void handle(Object param) {
        String reqId = UUID.randomUUID().toString();
        DispatchContext context = (DispatchContext) param;
        context.setReqId(reqId);
        Dispatch dispatch = context.getDispatch();
        String key = LOCK_KEY + dispatch.getOrderNo() + dispatch.getBizFlowNo();
        int retry = 0;
        boolean lock = tryLock(key, reqId);
        while (!lock && (++retry <= maxRetries)) {
            try {
                Thread.sleep(Math.min(sleepTime, 1000L));
            } catch (InterruptedException e) {
            }
            lock = tryLock(key, reqId);
        }
        if (!lock) {
            throw Exceptions.business("get lock fail, try later");
        }
    }

    private boolean tryLock(String key, String value) {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(
                key, value, Duration.ofMillis(keyTimeout)
        ));
    }

    @Override
    public void cleanup(Object param) {
        DispatchContext context = (DispatchContext) param;
        Dispatch dispatch = context.getDispatch();
        String reqId = context.getReqId();
        String key = LOCK_KEY + dispatch.getOrderNo() + dispatch.getBizFlowNo();
        String value = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(reqId) && reqId.equals(value)) {
            stringRedisTemplate.delete(key);
        }
    }
}