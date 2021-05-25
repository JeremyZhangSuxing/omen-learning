package com.omen.learning.java;

import com.omen.learning.common.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Knight
 * @date : 2021/5/25 1:46 下午
 */
public class OrderGeneratorDemo {

    int i = 0;
    private static final String ORDER_FLAG = "--->";
    private static final String ORDER_PREFIX = "tes-knight";

    public static void main(String[] args) {
        OrderGeneratorDemo orderGeneratorDemo = new OrderGeneratorDemo();
        long millis = System.currentTimeMillis();
        System.out.println("业务开始时间 ；   " + millis);
        for (int i = 0; i < 100; i++) {
            ExecutorService executorService = ThreadUtils.buildThreadPool();
            executorService.submit(orderGeneratorDemo::generateOrderNo);
        }
        System.out.println("耗费时间 ；   " + (System.currentTimeMillis() - millis));
    }

    /**
     * synchronize 保证线程安全
     */
    private synchronized void generateOrderNo() {
        String orderPrefix = ORDER_PREFIX + ORDER_FLAG + i;
        try {
            TimeUnit.MILLISECONDS.sleep(200L);
            i++;
            System.out.println(Thread.currentThread().getName() + "  synchronize 生成的单号：  " + orderPrefix);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /**
     * aqs 保证线程安全
     */
    private void generatorOrderNo() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(1L);
            String orderPrefix = ORDER_PREFIX + ORDER_FLAG + i;
            System.out.println("synchronize 生成的单号：  " + orderPrefix);
            i++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
