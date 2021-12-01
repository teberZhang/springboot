package com.example.springboot.common.dto;

import com.example.springboot.common.annotation.valid.PayValid;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Component
public class WXPayDto implements Serializable {

    private static final long serialVersionUID = 8969698091098391052L;

    // 单据编号
    @NotNull(message = "订单编号不可为空", groups = {PayValid.Unified.class})
    private String orderNo;

    // 支付方式（小程序支付、公众号支付等）
    @NotNull(message = "支付方式不可为空", groups = {PayValid.Unified.class})
    private String tradeType;

    // 支付网关（app、web、miniapp、pos、scan等）
    @NotNull(message = "支付网关不可为空", groups = {PayValid.Unified.class})
    private String payGateway;

    // 用户微信openId
//    @NotNull(message = "OpenId不可为空", groups = {PayValid.Unified.class})
    private String openId;

}
