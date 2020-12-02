package com.omen.learning.sample.test;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhang.suxing
 * @date 2020/11/12 21:41
 **/
@Service
public class TestService {

    AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    public void test() throws InterruptedException {
        if (!atomicBoolean.getAndSet(false)){
            System.err.println("job has been scheduled");
            return;
        }

        while (true) {
            Thread.sleep(100);
            System.out.println("this is answer");
        }

    }
}
