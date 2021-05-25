package com.omen.learning.java.support;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Knight
 * @date : 2021/5/24 1:49 下午
 */
public class LockInterruptTest {
    public static void main(String[] args) throws Exception {
        LockInterruptTest test = new LockInterruptTest();
        test.test();
        System.out.println("over ");
    }

    public void test() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (Exception e) {
                System.out.println("xxxxxxx");
            }
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }, "child thread -1");
        t1.start();
        Thread.sleep(1000);
//        lock.unlock();
        t1.interrupt();
        Thread.sleep(1000000);
    }

}
