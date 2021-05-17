package com.omen.learning.sample.recat;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @author : Knight
 * @date : 2021/5/17 2:39 下午
 */
public class FluxScheduleTest {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(300), Schedulers.newParallel("test..."))
                .subscribe(v -> System.out.println(Thread.currentThread().getName() + "---> " + v));


        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .publishOn(s)
                .map(i -> Thread.currentThread().getName() + " value " + i);
        new Thread(() -> flux.subscribe(System.out::println));
    }
}
