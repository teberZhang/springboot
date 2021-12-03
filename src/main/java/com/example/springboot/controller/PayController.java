package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.common.annotation.pay.AppletsPay;
import com.example.springboot.common.annotation.valid.PayValid;
import com.example.springboot.common.dto.WXPayDto;
import com.example.springboot.common.dto.WxPayValidDTO;
import com.example.springboot.service.api.PayServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/pay")
// 需要在 GlobalExceptionHandler 定义验证 violationException状态码:50013
// 依赖：hibernate-validator
public class PayController {

    @Autowired
    private PayServiceApi payServiceApi;

    // Validated:groups 分组验证
    @RequestMapping(value = "/wx/pay", method = RequestMethod.POST)
    @SpringLogs(serviceType="PayController.wxPay", description="微信支付:分组验证")
    public BaseResult<?> wxPay(@Validated({PayValid.Unified.class}) @RequestBody WXPayDto dto) {
        log.info("微信支付");
        return payServiceApi.wxPay(dto);
    }

    // Validated:groups 分组验证多个
    @RequestMapping(value = "/wx/appletsPay", method = RequestMethod.POST)
    @SpringLogs(serviceType="PayController.wxPayApplets", description="微信小程序支付:多个分组验证")
    public BaseResult<?> wxPayApplets(@Validated({PayValid.Unified.class, AppletsPay.class}) @RequestBody WXPayDto dto) {
        log.info("微信小程序支付");
        return payServiceApi.wxPay(dto);
    }

    // 普通验证
    @RequestMapping(value = "/wx/test", method = RequestMethod.POST)
    @SpringLogs(serviceType="PayController.test", description="微信支付:普通验证")
    public BaseResult<?> test(@Valid @RequestBody WxPayValidDTO dto){
        log.info("微信支付1");
        return BaseResult.ok(dto);
    }
}
