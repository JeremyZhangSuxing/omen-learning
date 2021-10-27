package com.omen.learning.sample.config;

import com.omen.learning.sample.support.ObjectMapperBuilder;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class RedissonConfiguration {

    private Config createConfig() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");


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

    @Bean
    public RedisScript<String> unlockScript() {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("META-INF/script/unlock.lua")));
        redisScript.setResultType(String.class);
        return redisScript;
    }

    @Bean
    public RedisScript<Long> reentrantLockScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("META-INF/script/reentrant_lock.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    @Bean
    public RedisScript<Long> reentrantUnlockScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("META-INF/script/reentrant_unlock.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}