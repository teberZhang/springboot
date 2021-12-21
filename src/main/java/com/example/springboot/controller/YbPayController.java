package com.example.springboot.controller;

import com.example.springboot.service.impl.YbPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/ybPay")
public class YbPayController {

    @Autowired
    private YbPayServiceImpl ybPayService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
        ybPayService.ybTest();
    }
}
