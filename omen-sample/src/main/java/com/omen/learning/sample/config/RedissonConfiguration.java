package com.omen.learning.sample.config;

import com.omen.learning.sample.support.ObjectMapperBuilder;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {

    private Config createConfig() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setDatabase(1);
        JsonJacksonCodec jsonJacksonCodec = new JsonJacksonCodec(ObjectMapperBuilder.build());
        config.setCodec(jsonJacksonCodec);
        return config;
    }

    /**
     * destroy 不可乱用 这里是 shutdown 一个 redissonClient instance
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        return Redisson.create(createConfig());
    }

}