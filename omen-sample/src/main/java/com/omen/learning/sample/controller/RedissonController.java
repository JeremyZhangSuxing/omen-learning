package com.omen.learning.sample.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : Knight
 * @date : 2021/10/29 10:43 上午
 */
@Slf4j
@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonController {
    private final RedissonClient redissonClient;
    private final ExecutorService commonInvokeExecutor;
    private static final int MAX_RETRY_TIMES = 3;
    @GetMapping("/lock")
    public String lock(@RequestParam String lockName, @RequestParam Long leaseTime) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockName);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(3);
        return "lock success!!";
    }

    @GetMapping("/unlock")
    public String unlock(@RequestParam String lockName) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockName);
        for (int i = 0; i < 5; i++) {
            lock.unlock();
            TimeUnit.SECONDS.sleep(1);
        }
        return "unlock success!!";
    }

    /**
     * 在分布式锁的场景下 如何实现可冲入锁以及使用 获取锁状态来实现一把轻量自旋锁
     * @param key
     */
    @GetMapping("/lockStatus/{key}")
    public void lockStatus(@PathVariable String key) throws InterruptedException {
        RLock lock = redissonClient.getLock(key);
        for (int i = 0; i < MAX_RETRY_TIMES; i++) {
            TimeUnit.MILLISECONDS.sleep(200);
            if (!lock.isLocked()) {
                log.info("execute business logic here");
            }
        }
        log.warn("fail to get lock, response error");
    }
}
