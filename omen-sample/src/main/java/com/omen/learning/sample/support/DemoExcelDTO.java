package com.omen.learning.sample.support;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoExcelDTO {
    @ExcelProperty(value = "用户名", index = 0)
    private String username;
    @ExcelProperty(value = "发货日期", index = 1, converter = TimeConverter.class)
    private Long dispatchDate;
    @ExcelProperty(value = "单据类型", index = 2, converter = BillTypeConverter.class)
    private BillType billType;
}
