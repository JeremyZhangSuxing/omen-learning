package com.omen.learning.sample.test;

import reactor.core.publisher.Mono;

/**
 * @author : Knight
 * @date : 2021/5/13 4:50 下午
 */
public class RxTest {
    public static void main(String[] args) {
        Mono.empty().subscribe(v -> System.out.println("111"));
        Mono.just("www.pornhub.com").subscribe(System.out::println);

    }

}
