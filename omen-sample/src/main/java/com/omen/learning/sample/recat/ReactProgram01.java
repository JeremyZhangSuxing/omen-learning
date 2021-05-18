package com.omen.learning.sample.recat;

import com.omen.learning.common.entity.BillDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

/**
 * @author : Knight
 * @date : 2021/5/18 3:34 下午
 */
public class ReactProgram01 {

    public static void creatProgramMatically() {
        Flux<Object> generate = Flux.generate(() -> 1, (state, sink) -> {
            sink.next("message #" + state);
            if (state == 10) {
                sink.complete();
            }
            return state + 1;
        });
        subscribeFlux("generateFlux", generate);
    }

    private static void subscribeFlux(String name, Flux<?> flux) {
        flux.doOnSubscribe(s -> System.out.println(name + ":"))
                .doOnNext(element -> System.out.println(element + "__"))
                .doOnComplete(() -> System.out.println("执行完成"))
                .subscribe();
    }

    /**
     * 自定义现车去执行目标代码
     * 实际使用的可指定特定的线程池
     */
    private static void createAsync() {
        Mono<String> callableMono = Mono.fromCallable(() -> Thread.currentThread().getName() + "  @" + LocalDateTime.now())
                .publishOn(Schedulers.elastic());
        blockMono("callableMono", callableMono);

        Mono<String> runnableMono = Mono.fromCallable(() -> Thread.currentThread().getName() + "  @" + LocalDateTime.now())
                .publishOn(Schedulers.elastic());
        blockMono("runnableMono", runnableMono);

        Mono<String> supplierMono = Mono.fromCallable(() -> Thread.currentThread().getName() + "  @" + LocalDateTime.now())
                .publishOn(Schedulers.elastic());
        blockMono("supplierMono", supplierMono);

    }

    /**
     * 与stream流不同 flatmap 可以从一个pipeline进入另一个pipeline
     */
    private static void mapVsFlatMap() {
        Flux<String> map = Flux.just(1, 2, 3, 4)
                .map(element -> "map id #" + element);
        subscribeFlux("fluxMap", map);
        Flux<String> flatMap = Flux.just(1, 2, 3, 4)
                .flatMap(v -> Mono.just(" flatMap id # " + v));
        subscribeFlux("fluxFlatMap", flatMap);
    }

    /**
     * 在react 中不是代码的前后来控制程序执行顺序，需要使用特定的api来指定
     */
    private static void userThenFlow() {
        Mono.just("word").map(v -> "hello1 " + v + " !")
                .doOnNext(System.out::println)
                .subscribe();

        Mono.just("word").map(v -> "hello2 " + v + " !")
                .doOnNext(System.out::println)
                .subscribe();
        //
        Mono<BillDTO> monoThen = Mono.just("word").map(v -> "hello " + v + " !")
                .doOnNext(System.out::println)
                .thenReturn(BillDTO.build());
        blockMono("thenFlow", monoThen);
    }


    /**
     * block api 是阻塞的虽然他也会打打开 pipeline，但是不推荐使用
     * block 是有返回值的，并且pipeline中的所有的数据处理完才会进入下一步
     */
    private static void blockMono(String monoName, Mono<?> mono) {
        mono.doOnSubscribe(s -> System.out.print(monoName + " ---> "))
                .doOnNext(v -> System.out.println(v + " ."))
                .block();
    }

    public static void main(String[] args) {
//        creatProgramMatically();
//        createAsync();
//        mapVsFlatMap();
        userThenFlow();
    }


}
