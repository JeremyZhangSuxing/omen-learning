package com.omen.learning.sample.mybatis.po;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DispatchBillInfo {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 发货单号
     */
    private String billNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 业务流水号
     */
    private String bizFlowNo;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 订单来源
     */
    private String billSource;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    /**
     * 下发wms时间
     */
    private LocalDateTime departureTime;

    /**
     * 订单创建时间
     */
    private LocalDateTime orderCreateDate;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 最新更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 是否删除
     */
    private Boolean deleted;
}