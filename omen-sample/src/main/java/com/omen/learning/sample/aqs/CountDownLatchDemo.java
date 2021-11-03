package com.omen.learning.sample.aqs;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author : Knight
 * @date : 2021/5/27 3:01 下午
 */
public class CountDownLatchDemo {
    private static final Random random = new Random();

    @AllArgsConstructor
    static class TaskSearch implements Runnable {
        private CountDownLatch countDownLatch;
        private Integer id;

        @Override
        public void run() {
            System.out.println("开始寻找---" + id + " 号龙珠");
            int seconds = random.nextInt(10) * 1000;
            try {
                Thread.sleep(seconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            System.out.println("花了  " + seconds + "  时间找到了" + id + "  号龙珠");
            countDownLatch.countDown();
        }
    }
    
    //static
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        CountDownLatch countDownLatch = new CountDownLatch(integers.size());
        for (Integer integer : integers) {
            Thread thread = new Thread(new TaskSearch(countDownLatch, integer));
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println("balls have been collected");
    }
}
