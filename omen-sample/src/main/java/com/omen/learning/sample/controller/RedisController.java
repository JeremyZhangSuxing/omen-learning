package com.omen.learning.sample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

/**
 * @author : Knight
 * @date : 2021/10/27 4:51 下午
 */
@RestController("/redis")
@RequiredArgsConstructor
public class RedisController {
    private final RedisScript<String> unlockScript;
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisScript<Long> reentrantLock;
    private final RedisScript<Long> reentrantUnlock;
    //todo add a test about unlock lua

    /**
     * 获取锁
     */
    public String tryLock(String lockName, long releaseTime) {
        // 存入的线程信息的前缀
        String key = UUID.randomUUID().toString();

        // 执行脚本
        Long execute = redisTemplate.execute(
                reentrantLock,
                Collections.singletonList(lockName),
                // hash value
                key + Thread.currentThread().getId(),
                releaseTime);

        if (Objects.requireNonNull(execute).intValue() == 1) {
            return key;
        } else {
            return null;
        }
    }

    /**
     * 解锁
     *
     * @param lockName
     * @param key
     */
    public void unlock(String lockName, String key) {
        redisTemplate.execute(unlockScript,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId()
        );
    }
}
