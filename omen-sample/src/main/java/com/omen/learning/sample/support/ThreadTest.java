package com.omen.learning.sample.support;

/**
 * @author : Knight
 * @date : 2021/5/6 10:32 上午
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 循环中检测当前线程的中断状态
            for (int i = 0; i < Integer.MAX_VALUE && !Thread.currentThread().isInterrupted(); i++) {
                System.out.println(i);
            }
        });
        thread.start();
        Thread.sleep(5000L);
        System.out.println("--------");
        thread.interrupt();
    }
}
