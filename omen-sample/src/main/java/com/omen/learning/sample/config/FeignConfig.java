package com.omen.learning.sample.config;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suxing.zhang
 * @date 2021/3/8 22:39
 **/
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    /**
     * 文件上传时  指定文件解码器
     */
    @Bean
    public Encoder feignFormEncoder(){
        return new SpringFormEncoder();
    }

}
