package com.omen.learning.sample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

/**
 * @author : Knight
 * @date : 2021/10/27 4:51 下午
 */
@RequestMapping("/redis")
@RequiredArgsConstructor
@RestController
public class RedisController {
    private final RedisTemplate<String, Object> redisTemplate;
    private final @Qualifier("reentrantLockScript")
    RedisScript<Long> reentrantLockScript;
    private final @Qualifier("reentrantUnlockScript")
    RedisScript<Long> reentrantUnlockScript;


    /**
     * 自己实现一把分布式锁
     * 【加锁】
     */
    @PutMapping("/lock")
    public String reentrantLock(@RequestParam String lockName, @RequestParam String releaseTime) {
        return tryLock(lockName, Long.parseLong(releaseTime));
    }

    /**
     * 自己实现一把分布式锁
     * 【解锁】
     */
    @PutMapping("/unlock")
    public String reentrantUnlock(@RequestParam String lockName, @RequestParam String key) {
        unlock(lockName, key);
        return "unlock success!!!";
    }

    /**
     * 获取锁
     */
    public String tryLock(String lockName, long releaseTime) {
        // 存入的线程信息的前缀
        String key = UUID.randomUUID().toString();

        // 执行脚本
        Long execute = redisTemplate.execute(
                reentrantLockScript,
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
     */
    public void unlock(String lockName, String key) {
        redisTemplate.execute(reentrantUnlockScript,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId()
        );
    }


}
