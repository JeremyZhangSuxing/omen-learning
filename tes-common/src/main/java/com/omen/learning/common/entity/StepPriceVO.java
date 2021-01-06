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
public class StepPriceVO {
    private Long startTime;

    @JsonProperty("finishTime")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date endTime;

    private BigDecimal grossPrice;
    private BigDecimal discountOff;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime now;


    public static StepPriceVO buildOneObject() {
        StepPriceVO vo = new StepPriceVO();
        vo.setDiscountOff(BigDecimal.ONE);
        vo.setEndTime(new Date());
        vo.setStartTime(111L);
        vo.setGrossPrice(BigDecimal.TEN);
        vo.setNow(LocalDateTime.now());
        return vo;
    }

    public static List<StepPriceVO> buildManyObject() {
        StepPriceVO vo = new StepPriceVO();
        vo.setDiscountOff(BigDecimal.ONE);
        vo.setEndTime(new Date());
        vo.setStartTime(111L);
        vo.setGrossPrice(BigDecimal.TEN);
        vo.setNow(LocalDateTime.now());
        StepPriceVO vo1 = new StepPriceVO();
        BeanUtils.copyProperties(vo,vo1);
        return Arrays.asList(vo,vo1);
    }
}
