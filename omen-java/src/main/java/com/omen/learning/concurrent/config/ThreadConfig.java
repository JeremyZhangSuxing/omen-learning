package com.omen.learning.concurrent.config;

import com.omen.learning.concurrent.config.support.LocalThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : Knight
 * @date : 2020/11/8 10:42 上午
 */
@Configuration
public class ThreadConfig {
    /**
     * 此线程池用来提高非批量接口调用效率
     */
    @Bean
    public ExecutorService commonInvokeExector() {
        return new ThreadPoolExecutor(10,
                20,
                1, TimeUnit.MINUTES,
                new SynchronousQueue<>(),
                new LocalThreadFactory("common_invoker_thread_"),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }
}
