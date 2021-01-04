package com.omen.learning.sample.support;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : Knight
 * @date : 2021/1/4 2:51 下午
 */
@Data
public class DemoExcelUploadDTO {
    @ExcelProperty(value = "姓名")
    private String name;
    @ExcelProperty(value = "数量")
    private Integer quantity;
    @ExcelProperty(value = "金额")
    private BigDecimal amount;
    @ExcelProperty(value = "更新时间")
    private Date updateTime;
}
