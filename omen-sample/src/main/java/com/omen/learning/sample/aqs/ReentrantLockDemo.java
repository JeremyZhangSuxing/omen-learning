package com.omen.learning.sample.aqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Knight
 * @date : 2021/11/3 2:58 下午
 */
@Slf4j
@Service
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Integer atomicInteger = new Integer(0);

        ReentrantLock lock = new ReentrantLock();
        for (int j = 0; j < 20; j++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                testReentrantLock(atomicInteger);
                System.out.println("业务执行结束" + atomicInteger);
                lock.unlock();
            }, "test thread" + Thread.currentThread().getId());
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println("-----" + atomicInteger);
    }

    public static void testReentrantLock(Integer atomicInteger) {
        int i = atomicInteger + 1;
        System.out.println("++++++++++++" + i);
    }
}
