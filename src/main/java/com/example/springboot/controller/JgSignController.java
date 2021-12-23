package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.common.dto.jg.DecryptDto;
import com.example.springboot.common.dto.jg.EncryptDto;
import com.example.springboot.service.api.JgSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/jg")
public class JgSignController {

    @Autowired
    private JgSignService jgSignService;

    // 监管平台加密
    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    @SpringLogs(serviceType="JgSignController.encrypt", description="监管加密")
    public BaseResult<?> encrypt(@RequestBody EncryptDto encryptDto) {
        return jgSignService.encrypt(encryptDto);
    }

    // 监管平台解密
    @RequestMapping(value = "/decrypt", method = RequestMethod.POST)
    @SpringLogs(serviceType="JgSignController.decrypt", description="监管解密")
    public BaseResult<?> decrypt(@RequestBody DecryptDto decryptDto) {
        return jgSignService.decrypt(decryptDto);
    }
}
