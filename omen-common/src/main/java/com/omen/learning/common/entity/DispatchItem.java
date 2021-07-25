package com.omen.learning.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
/**
 * @author suxing.zhang
 * @date 2021/7/18 15:21
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchItem {
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
     * 序号
     */
    private String sequence;
    /**
     * 条码
     */
    @NotBlank
    private String barCode;
    /**
     * 货号
     */
    @NotBlank
    private String materialCode;
    /**
     * 尺码名称
     */
    @NotBlank
    private String sizeName;
    /**
     * 尺码代码
     */
    @NotBlank
    private String sizeCode;
    /**
     * 预计数量
     */
    @NotNull
    private Integer amount;
    /**
     * 实物占库单号
     */
    private Long physicalOccupyNo;
    /**
     * 占库数量
     */
    private Integer occupyAmount;
    /**
     * 结算金额(总价)
     * PayAmount= ExpectQty* BalaPrice
     */
    @NotNull
    private BigDecimal payAmount;
    /**
     * 零售价（吊牌价）
     */
    @NotNull
    private BigDecimal retailPrice;
    /**
     * 结算价（单价）
     */
    @NotNull
    private BigDecimal price;
    /**
     * 商家优惠券金额
     */
    private BigDecimal shopDiscountAmount;
    /**
     * 平台优惠券金额
     */
    private BigDecimal platformDiscountAmount;
    /**
     * 平台销售单价
     */
    private BigDecimal platformSalePrice;

    private Byte deleted;
}