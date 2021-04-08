package com.omen.learning.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author knight
 * @since 2020/01/21
 */
@Data
public class JackSonDemo {
    private Long startTime;

    @JsonProperty("finishTime")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date endTime;
    private BigDecimal grossPrice;
    private BigDecimal discountOff;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime now;

    public static JackSonDemo buildOneObject() {
        JackSonDemo vo = new JackSonDemo();
        vo.setDiscountOff(BigDecimal.ONE);
        vo.setEndTime(new Date());
        vo.setStartTime(111L);
        vo.setGrossPrice(BigDecimal.TEN);
        vo.setNow(LocalDateTime.now());
        return vo;
    }

    public static List<JackSonDemo> buildManyObject() {
        JackSonDemo vo = new JackSonDemo();
        vo.setDiscountOff(BigDecimal.ONE);
        vo.setEndTime(new Date());
        vo.setStartTime(111L);
        vo.setGrossPrice(BigDecimal.TEN);
        vo.setNow(LocalDateTime.now());
        JackSonDemo vo1 = new JackSonDemo();
        BeanUtils.copyProperties(vo,vo1);
        return Arrays.asList(vo,vo1);
    }
}
