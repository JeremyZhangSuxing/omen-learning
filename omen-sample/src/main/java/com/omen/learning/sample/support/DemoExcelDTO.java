package com.omen.learning.sample.support;

import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

@Data
public class DemoExcelDTO {
    @ExcelField(name = "用户名")
    private String username;
    @ExcelField(value = "出生年月",dateFormat = "yyyy/MM/dd")
    private String date;
}
