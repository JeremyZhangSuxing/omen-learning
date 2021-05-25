package com.omen.learning.java.support;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Knight
 * @date : 2020/11/8 4:00 下午
 */
public class LocalThreadFactory implements ThreadFactory {

    private final String threadName;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public LocalThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadName + threadNumber.getAndIncrement());
    }
}
