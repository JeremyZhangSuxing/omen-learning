package com.omen.learning.sample;

import com.omen.learning.bean.test.annotattion.EnableBeanRegistry;
import com.omen.learning.common.annotation.EnableTokenValidate;
import com.omen.learning.email.annotation.EnableEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author : Knight
 * @date : 2020/11/8 10:57 上午
 */
@SpringBootApplication
@EnableTokenValidate
@EnableEmail
@EnableBeanRegistry
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
