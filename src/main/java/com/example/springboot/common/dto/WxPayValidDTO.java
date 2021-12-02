package com.example.springboot.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class WxPayValidDTO implements Serializable {

    private static final long serialVersionUID = -5143220336059430879L;

    // 单据编号
    @NotBlank(message = "订单编号不可为空")
    private String orderNo;

    // 支付方式（小程序支付、公众号支付等）
    @NotBlank(message = "支付方式不可为空")
    private String tradeType;

    // 支付网关（app、web、miniapp、pos、scan等）
    @NotNull(message = "支付网关不可为空")
    private String payGateway;
}
