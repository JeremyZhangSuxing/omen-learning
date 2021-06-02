package com.omen.learning.sample.aqs;

import java.util.concurrent.CompletableFuture;


/**
 * @author : Knight
 * @date : 2021/5/31 5:16 下午
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> orderAirplane = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询航班");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订购航班");
            return "航班信息";
        });

        CompletableFuture<String> orderHotel = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询酒店");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订购酒店");
            return "酒店信息";
        });

        CompletableFuture<String> hireCar = orderHotel.thenCombine(orderAirplane, (airplane, hotel) -> {
            System.out.println("根据航班和酒店查询汽车");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("订购汽车信息");
            return "汽车信息";
        });
        System.out.println(hireCar.join());
    }
}
