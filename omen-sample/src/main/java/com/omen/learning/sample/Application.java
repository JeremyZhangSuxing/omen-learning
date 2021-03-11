package com.omen.learning.sample;

import com.omen.learning.bean.test.annotattion.EnableBeanRegistry;
import com.omen.learning.common.annotation.EnableTokenValidate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : Knight
 * @date : 2020/11/8 10:57 上午
 */
@SpringBootApplication
@EnableTokenValidate
@EnableBeanRegistry
@EnableCaching
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
