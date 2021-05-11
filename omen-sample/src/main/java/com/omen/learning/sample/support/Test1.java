package com.omen.learning.sample.support;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Knight
 * @date : 2021/2/21 4:08 下午
 */
public class Test1 {
    public static void main(String[] args) {
        LocalDate afterDate = LocalDate.of(2021, 3, 31);
        LocalDate beforeDate = LocalDate.of(2021, 2, 28);
        //月数
        long betweenMONTHS = ChronoUnit.MONTHS.between(beforeDate, afterDate);
        //年数
        long between1YEARS = ChronoUnit.YEARS.between(beforeDate, afterDate);
        //天数
        long between1DAYS = ChronoUnit.DAYS.between(beforeDate, afterDate);
        System.out.println(betweenMONTHS);
        System.out.println(between1YEARS);
        System.out.println(between1DAYS);
        List<String> strings = Arrays.asList("anna.tsai@wework.cn", "we-cn-00879@wework.cn", "jessie.liu@themetathink.com");
        strings.forEach(System.out::println);
        System.out.println("当前线程的状态---");

    }


}