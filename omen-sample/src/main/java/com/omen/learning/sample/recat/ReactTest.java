package com.omen.learning.sample.recat;

import com.weweibuy.framework.common.core.exception.Exceptions;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * @author : Knight
 * @date : 2021/5/17 11:37 上午
 */
public class ReactTest {
    public static void main(String[] args) {
        Flux<String> just = Flux
                .just("foo", "bar", "foobar")
                .map(v -> {
                    if (v.startsWith("b")) {
                        throw Exceptions.badRequestParam();
                    } else {
                        return v + "---> the success one";
                    }
                });
        just.subscribe(System.out::println,
                error -> System.err.println(Thread.currentThread().getName() + "--->ERROR :  " + error.getMessage()),
                () -> System.out.println("project done"));

        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(ss);

        System.out.println(Thread.currentThread().getName() + "--------- test ");
        Flux.range(0, 10)
                .doOnRequest(r -> System.out.println("request of --->" + r))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }

                    @Override
                    public void hookOnComplete() {
                        System.out.println("request has finished---");
                    }
                });
    }

}
