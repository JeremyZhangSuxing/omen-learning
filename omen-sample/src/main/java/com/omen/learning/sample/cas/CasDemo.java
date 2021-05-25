package com.omen.learning.sample.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang.suxing
 * @date 2020/3/18 21:17
 **/
public class CasDemo {
    private static int count = 0;

    private static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5L);
        int expect;
        //更新了才会跳出就循环
        while (!compareAndSwap(expect = getCount(), expect + 1)) {
            System.out.println("并发竞争中。。。。。");
        }
    }

    /**
     * 对流程拆分 在变更数据时加乐观锁
     */
    private static synchronized boolean compareAndSwap(int expect, int newCount) {
        if (expect == getCount()) {
            count = newCount;
            return true;
        }
        return false;
    }

    private static int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "耗费时间" + (end - start) + "count = " + count);
    }
}
