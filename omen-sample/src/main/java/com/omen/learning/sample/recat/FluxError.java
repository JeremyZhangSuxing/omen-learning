package com.omen.learning.sample.recat;

import com.weweibuy.framework.common.core.exception.Exceptions;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author : Knight
 * @date : 2021/5/17 3:07 下午
 */
public class FluxError {
    public static void main(String[] args) {
        Flux<String> stringFlux = Flux.just(1, 2, 0)
                .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
                .onErrorReturn("Divided by zero :(");// error handling example
        stringFlux.subscribe(System.out::println);


        Flux<String> s = Flux.range(1, 10)
                .map(FluxError::doSomethingdangerous)
                .map(FluxError::doSecondTransform);
        s.subscribe(value -> System.out.println("RECEIVED " + value),
                error -> System.err.println("CAUGHT " + error)
        );

        /**
         * ==> catch exception  and throw
         */
//        Flux.just("timeout1")
//                .flatMap(FluxError::callExternalService)
//                .onErrorMap(original -> Exceptions.business("-1", "error--->" + original)
//                ).subscribe();
//
        LongAdder failureStat = new LongAdder();
        Flux.just("test1")
                .flatMap(k -> callExternalService(k)
                        .doOnError(e -> {
                            failureStat.increment();
                            System.out.println("uh oh, falling back, service failed for key " + k);
                        })

                ).subscribe();
    }


    private static Flux<String> callExternalService(String s) {
        if (!s.equals("test")) {
            throw Exceptions.business("-2", "callExternalService");
        }
        return Flux.just(s);
    }


    /**
     * log message here
     */
    private static String doSomethingdangerous(Integer v) {
        if (v > 9) {
            throw Exceptions.badRequestParam();
        }
        System.out.println("doSomethingdangerous --" + v);
        return v.toString();
    }

    /**
     * 再次调用
     */
    private static String doSecondTransform(String v) {
        return "transfer---" + v;
    }

}
