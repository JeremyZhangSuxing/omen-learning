package com.omen.learning.sample.test;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author jeremy.zhang
 * @date 2022-09-15
 */
@Component
public class TestRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            System.out.println("jeremy good morning ......");
        }
    }
}
