package com.omen.learning.sample.aqs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : Knight
 * @date : 2021/5/31 5:38 下午
 */
public class CompleteFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//            System.out.println("cf 任务执行开始");
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//
//            }
//            System.out.println("cf 任务执行结束");
//            return "楼下小黑哥";
//        });
//
//        Executors.newSingleThreadScheduledExecutor().execute(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//
//            }
//            System.out.println("主动设置 cf 任务结果");
//            // 设置任务结果，由于 cf 任务未执行结束，结果返回 true
//           cf.complete("程序通事");
//        });
//        System.out.println("get:" + cf.get());
//        TimeUnit.SECONDS.sleep(10);
//        System.out.println("get:" + cf.get());
        CompletableFuture<String> cf
                = CompletableFuture.supplyAsync(() -> "hello,楼下小黑哥")// 1
                .thenApply(s -> s + "@程序通事") // 2
                .thenApply(String::toUpperCase); // 3
        System.out.println(cf.join());

    }


}
