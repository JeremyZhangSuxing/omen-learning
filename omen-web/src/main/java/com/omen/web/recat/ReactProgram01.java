package com.omen.web.recat;

import com.omen.web.domain.BillDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple3;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Knight
 * @date : 2021/5/18 3:34 下午
 */
public class ReactProgram01 {

    /**
     * 程序式创建flux
     */
    public static void createFluxProgrammatically() {
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
                .doOnNext(element -> System.out.println(element + "#"))
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
     * Mono and Flux 之间的互转
     */
    private static void changeFluxIntoMono() {
        Flux<Integer> flux = Mono.just(1).flux();
        subscribeFlux("monoToFlux", flux);
        Mono<List<Integer>> listMono = Flux.just(1, 2, 3).collectSortedList();
        blockMono("listMono", listMono);
    }

    /**
     * zip 返回多个Mono
     */
    private static void zipMonoFlux() {
        String userId = "max";
        Mono<String> just = Mono.just(userId + "的详细信息");
        Mono<String> just1 = Mono.just(BillDTO.build() + "最新的订单");
        Mono<String> just2 = Mono.just(userId + "的最新评论");
        Mono<Tuple3<String, String, String>> tuple3Mono = Mono.zip(just, just1, just2)
                .doOnNext(t -> System.out.printf("%s的主页，%s, %s, %s%n", userId, t.getT1(), t.getT2(), t.getT3()));
        blockMono("tuple3Mono", tuple3Mono);
    }

    private static void handlerError() {
        //1.
//        Flux<String> anError = Flux.range(1, 10).map(
//                i -> {
//                    if (i > 5) {
//                        throw new RuntimeException("this is an error");
//                    }
//                    return "#  " + i;
//                });
//        subscribeFlux("anError", anError);
        //2. 这个要和 throw exception 区分开
        Flux<Mono<?>> monoFlux = Flux.range(1, 10).map(
                i -> {
                    if (i > 5) {
                        return Mono.error(new RuntimeException("this is an error"));
                    }
                    return Mono.just("item #" + i);
                });
        subscribeFlux("anError", monoFlux);
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
        mapVsFlatMap();
//        userThenFlow();
//        changeFluxIntoMono();
//        zipMonoFlux();
//        handlerError();

    }


}
