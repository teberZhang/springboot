package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.valid.PayValid;
import com.example.springboot.common.dto.WXPayDto;
import com.example.springboot.service.api.PayServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayServiceApi payServiceApi;

    @RequestMapping(value = "/wx/pay", method = RequestMethod.POST)
    public BaseResult<?> wxPay(@Validated(PayValid.Unified.class) @RequestBody WXPayDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("error");
        }
        log.info("微信支付");
        return payServiceApi.wxPay(dto);
    }
}
