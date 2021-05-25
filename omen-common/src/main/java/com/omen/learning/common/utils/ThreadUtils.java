package com.omen.learning.common.utils;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang.suxing
 * @date 2020/3/19 21:23
 **/
public class ThreadUtils {

    public static ExecutorService buildThreadPool() {
        return new ThreadPoolExecutor(
                10, 50, 3, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(5000),
                new CustomizableThreadFactory("test-thread-common-"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}