package com.omen.learning.sample;

import com.omen.learning.common.annotation.EnableServiceException;
import com.omen.learning.email.annotation.EnableEmail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Knight
 * @date : 2020/11/8 10:57 上午
 */
@SpringBootApplication
@EnableServiceException
@EnableEmail
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
