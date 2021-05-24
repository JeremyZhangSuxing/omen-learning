package com.omen.learning.sample.pattern;

/**
 * @author : Knight
 * @date : 2021/5/21 3:53 下午
 */
public class TestPatterns {
    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 20; i++) {
//            Thread thread = new Thread(() -> {
//                Singleton instance = Singleton.getInstance();
//                System.out.println("--->  " + instance.hashCode());
//            });
//            thread.start();
//        }
//        Thread.sleep(300L);
//        System.out.println(Singleton.getSize());
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());

    }

}
