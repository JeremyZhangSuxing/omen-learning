package com.omen.learning.sample.support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * @author : Knight
 * @date : 2021/2/19 10:59 上午
 */
public class Test {
    public static void main(String[] args) {
        //sample1: 2021-01-02~2021-03-12  预计结果  353.23
        //sample2: 2021-01-31~2021-02-16  预期结果  90.55
        //sample3: 2021-01-02~2021-07-04  预计结果  914.52
        //sample4: 2021-01-02~2021-01-30  预计结果  140.32
        BigDecimal price = BigDecimal.valueOf(60);
        LocalDate start = LocalDateTime.of(2021, 1, 2, 0, 0, 0).toLocalDate();
        LocalDate end = LocalDateTime.of(2021, 3, 12, 0, 0, 0).toLocalDate();

        Period period = Period.between(start, end);
        System.out.println("年:" + period.getYears() + ";  月：" + period.getMonths() + ";  日：" + period.getDays());
        long countMonth = period.getYears() * 12L + period.getMonths();
        //两个月相邻
        if (countMonth <= 1 && start.getMonthValue() != end.getMonthValue()) {
            BigDecimal bigDecimal = countHeadAndTail(start, end, price).setScale(2, RoundingMode.HALF_UP);
            //count 求和
            System.out.println("周期不足两月且大于一个月" + bigDecimal);
        }
        //两个月之间是有整月间隔的
        if (countMonth > 1) {
            BigDecimal count = BigDecimal.valueOf(countMonth - 1).multiply(price).add(countHeadAndTail(start, end, price)).setScale(2, RoundingMode.HALF_UP);
            System.out.println("周期大于两个月" + count);
        }
        //两个时间点在相同月份
        if (countMonth < 1 && start.getMonthValue() == end.getMonthValue()) {
            int days = period.getDays() + 1;
            long monthValue = start.lengthOfMonth();
            System.out.println("周期不足一个月" + price.multiply(BigDecimal.valueOf(days)).divide(BigDecimal.valueOf(monthValue), 2, RoundingMode.HALF_UP));
        }
    }

    public static BigDecimal countHeadAndTail(LocalDate start, LocalDate end, BigDecimal price) {
        int startMon = start.lengthOfMonth();
        int endMon = end.lengthOfMonth();
        int dayOfMonth = start.getDayOfMonth();
        long firstGap = startMon - dayOfMonth + 1L;
        BigDecimal firstMonth = price.multiply(BigDecimal.valueOf(firstGap)).divide(BigDecimal.valueOf(startMon), 3, RoundingMode.HALF_UP);
        System.out.println("相邻月 第一月天数" + firstGap);
        int dayOfMonth2 = end.getDayOfMonth();
        System.out.println("相邻月 第二月天数" + dayOfMonth2);
        BigDecimal secondMonth = price.multiply(BigDecimal.valueOf(dayOfMonth2)).divide(BigDecimal.valueOf(endMon), 3, RoundingMode.HALF_UP);
        System.out.println("头尾月金额：" + firstMonth.add(secondMonth).setScale(2, RoundingMode.HALF_UP));
        return firstMonth.add(secondMonth);
    }
}
