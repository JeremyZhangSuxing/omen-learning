package com.omen.learning.sample.mybatis.po;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DispatchBillDetail {
    /**
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
     * 商品条码
     */
    private String sku;

    /**
     * 尺码代码
     */
    private String sizeCode;

    /**
     * 尺码名称
     */
    private String sizeName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 占库单号
     */
    private Long physicalOccupyNo;

    /**
     * 占库数量
     */
    private Integer occupyAmout;

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