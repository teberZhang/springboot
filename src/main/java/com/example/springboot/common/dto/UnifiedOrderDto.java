package com.example.springboot.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UnifiedOrderDto implements Serializable {

    private static final long serialVersionUID = 1144609778445743887L;

    // 单据编号
    private String orderNo;

    // 机构ID
    private Integer orgId;

    // 机构编码
    private String orgCode;

    // 订单来源标识（SDF、XYY等）
    private String orderSource;

    // 订单来源描述（用于商品名称展示）
    private String orderSourceDesc;

    // 订单类型（问诊单、处方单等）
    private String orderType;

    // 订单类型描述（用于商品展示）
    private String orderTypeDesc;

    // 支付渠道（微信支付、支付宝支付等）
    private String payChannel;

    // 支付方式（小程序支付、公众号支付等）
    private String tradeType;

    // 支付网关（app、web、miniapp、pos、scan等）
    private String payGateway;

    // 金额
    private BigDecimal amount;

    // 是否分账 Y-是 N-否
    private String profitSharing;

    // 用户微信openId
    private String openId;

    // 回调通知地址
    private String notifyUrl;

    // 请求ip地址
    private String ipAddr;

    // 是否平台收款
    private boolean isPlatformReceive;
}
