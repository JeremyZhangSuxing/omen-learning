package com.omen.learning.common.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author suxing.zhang
 * @date 2021/7/18 15:23
 **/
@Data
@Builder
public class Dispatch {
    /**
     * 发货单号 ods 自己生成
     */
    private String billNo;
    /**
     * 订单号 又订单管理系统生成 且与业务流水号在一起用于支持幂等
     */
    private String orderNo;
    /**
     * 业务流水号
     */
    private String BizFlowNo;
    /**
     * 业务类型
     */
    private String bizType;

    private Long id;
    /**
     * 订单总金额
     */
    private BigDecimal amount;
    /**
     * 预计数量
     */
    private Integer expectAmount;
    /**
     * sku数量
     */
    private Integer totalRecordNum;
    /**
     * 实际发货总数量
     */
    private Integer totalActualShipment;
    /**
     * 预计到达时间
     */
    private Date expectArrivalTime;
    /**
     * 下发时间
     */
    private Date departureTime;
    /**
     * 上游订单创建时间(oms订单创建时间)
     */
    private Date billDate;
    /**
     * 平台订单下发时间
     */
    private Date orderTime;
    /**
     * 收件人姓名
     */
    @NotBlank
    private String contact;
    /**
     * 收件省
     */
    @NotBlank
    private String province;
    /**
     * 收件市
     */
    @NotBlank
    private String city;
    /**
     * 收件区
     */
    @NotBlank
    private String region;
    /**
     * 收件地址
     */
    @NotBlank
    private String address;
    /**
     * 收件人手机号
     */
    @NotBlank
    private String phone;
    /**
     * 备注
     */
    private String remark;

}
