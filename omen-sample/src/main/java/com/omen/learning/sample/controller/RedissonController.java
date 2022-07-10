package com.omen.learning.sample.controller;

import lombok.RequiredArgsConstructor;
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
@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonController {
    private final RedissonClient redissonClient;
    private final ExecutorService commonInvokeExecutor;

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
     * 在分布式所的场景下
     * @param key
     */
    @GetMapping("/lockStatus/{key}")
    public void lockStatus(@PathVariable String key) {

        RLock lock = redissonClient.getLock(key);
    }
}
