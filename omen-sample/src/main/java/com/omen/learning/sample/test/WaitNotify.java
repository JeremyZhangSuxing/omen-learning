package com.omen.learning.sample.test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by j_zhan on 2016/7/6.
 * synchronize
 *
 *
 */
public class WaitNotify {
    static boolean flag = true;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Wait(), "wait thread");
        threadA.start();
        TimeUnit.SECONDS.sleep(2);
        Thread threadB = new Thread(new Notify(), "notify thread");
        threadB.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true");
                        lock.wait();
                    } catch (InterruptedException e) {
                        //modify thread flag
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + LocalDateTime.now().toString() + " flag is false");
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                flag = false;
                lock.notifyAll();
                try {
                    System.out.println(Thread.currentThread() + LocalDateTime.now().toString());
                    TimeUnit.SECONDS.sleep(7);
                } catch (InterruptedException e) {
                    //modify thread flag
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }
}