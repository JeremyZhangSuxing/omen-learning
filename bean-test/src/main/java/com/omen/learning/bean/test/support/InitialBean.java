package com.omen.learning.bean.test.support;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Knight
 * @date : 2020/11/15 3:49 下午
 */
public class InitialBean {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 5, 6, 8, 44, 33, 25);

        integers.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        })
                .collect(Collectors.toList()).forEach(v-> System.out.println(v));
    }
}
