package com.omen.learning.sample.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author jeremy.zhang
 * @date 2022-09-27
 */
public class LocalBloomFilter {
    private static final int SIZE = 1000000;
    public static final double FPP = 0.01;

    private static final BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), SIZE, FPP);
    private static int total = 1000000;

    public static void main(String[] args) {
        for (int i = 0; i < total; i++) {
            bloomFilter.put(i);
        }


        int count = 0;
        for (int i = total; i < total + 100000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "--- 误判了");
            }
        }
        //误判率  和设置的目标值 大差不差
        System.out.println("总的误判数量 ---"+ count);
    }
}
