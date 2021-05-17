package com.omen.learning.sample.test;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author : Knight
 * @date : 2020/12/15 6:59 下午
 */
public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(SynchronizedTest::method2);
        thread.start();
        System.out.println("锁未释放");
        method1();

    }


    static synchronized void method1() {
        System.out.println("method1---" + Thread.currentThread().getName());
    }

    @SneakyThrows
    static synchronized void method2() {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("method2---" + Thread.currentThread().getName());
    }


}