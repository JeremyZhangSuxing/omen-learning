package com.omen.learning.sample.controller;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jeremy.zhang
 * @date 2022-09-26
 */
@RestController
@RequiredArgsConstructor
public class BloomFilterController {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        config.useSingleServer().setPassword("");
        RedissonClient redissonClient = Redisson.create(config);


        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("jeremy");
        //参数的设置要看实际的业务场景
        bloomFilter.tryInit(100000000L,0.01);
        bloomFilter.add("10086");

        System.out.println("================    "+bloomFilter.contains("1223456"));
        System.out.println("================    "+bloomFilter.contains("10086"));
    }
}
