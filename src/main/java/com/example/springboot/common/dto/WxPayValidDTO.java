package com.example.springboot.common.dto;

import com.example.springboot.common.annotation.valid.EnumValue;
import com.example.springboot.common.enums.OrderStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class WxPayValidDTO implements Serializable {

    private static final long serialVersionUID = -5143220336059430879L;

    // 单据编号
    @NotBlank(message = "订单编号不可为空")
    @Length(min = 10, max = 64, message = "订单号长度10-64个字符")
    private String orderNo;

    // 支付方式（小程序支付、公众号支付等）
    @NotBlank(message = "支付方式不可为空")
    private String tradeType;

    // 支付网关（app、web、miniapp、pos、scan等）
    @NotNull(message = "支付网关不可为空")
    private String payGateway;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不合法")
    private String phone;

    /***
     * 账号状态枚举类验证
     */
    @EnumValue(enumClass= OrderStatusEnum.class, enumMethod="isValidStatus", message = "传递的订单状态不正确")
    private String status;
}
