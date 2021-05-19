package com.omen.web.recat;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

/**
 * @author : Knight
 * @date : 2021/5/17 3:07 下午
 */
public class FluxApiDemo {
    public static void main(String[] args) throws InterruptedException {
        // https://stackoverflow.com/questions/59607165/what-is-the-difference-between-block-subscribe-and-subscribe
//        Mono.delay(Duration.ofMillis(10)).map(d -> {
//            System.out.println(d);
//            return d;
//        }).block();

//        Mono.delay(Duration.ofMillis(10)).map(d -> {
//            System.out.println(d);
//            return d;
//        }).subscribe(System.out::println);
//        TimeUnit.MILLISECONDS.sleep(200);

        CountDownLatch cdl = new CountDownLatch(1);
        Mono.delay(Duration.ofMillis(10))
                .map(d -> {
                    System.out.println(d);
                    return d;
                })
                .doOnTerminate(cdl::countDown)
                .subscribe(System.out::println);
        cdl.await();
    }

}
