package com.omen.learning.sample.support;

import com.github.houbb.csv.annotation.Csv;
import lombok.Data;

/**
 * @author : Knight
 * @date : 2021/1/14 2:57 下午
 */
@Data
public class DemoCsvDTO {
    @Csv(label = "用户名")
    private String username;
    @Csv(label = "发货日期")
    private Long dispatchDate;
    @Csv(label = "单据类型")
    private BillType billType;
}
