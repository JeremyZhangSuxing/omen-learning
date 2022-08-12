package com.omen.learning.sample.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author suxing.zhang
 * @date 2021/3/8 22:39
 * <>不需要给这个指定这个为 一个配置累 官方文档有写明</>
 **/
public class FeignConfig {

    @Bean
    public Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 文件上传时  指定文件解码器
     */
    @Bean
    public Encoder feignFormEncoder(){
        return new SpringFormEncoder();
    }

//    @Bean
//    public Retryer retryer(){
//        return new Retryer.NEVER_RETRY;
//
//    }

    @Bean
    public Feign.Builder orderFeignBuilder() {
        return Feign.builder()
                .options(new Request.Options(300, TimeUnit.MILLISECONDS, 300, TimeUnit.MILLISECONDS, true));
    }
}
