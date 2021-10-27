package com.omen.learning.sample.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Knight
 * @date : 2021/10/27 4:51 下午
 */
@RestController("/redis")
@RequiredArgsConstructor
public class RedisController {
    private final RedisScript<String> unlockScript;
    private final StringRedisTemplate stringRedisTemplate;

    //todo add a test about unlock lua
}
