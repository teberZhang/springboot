package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.entity.AdminUser;
import com.example.springboot.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisOperateController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/getAdmin", method = RequestMethod.POST)
    public BaseResult<AdminUser> getAdmin(@RequestBody AdminUser user) {

        return BaseResult.ok(adminUserService.getAdmin(user));
    }

    @RequestMapping(value = "/setAdmin", method = RequestMethod.POST)
    public BaseResult<?> setAdmin(@RequestBody AdminUser user) {

        return BaseResult.ok(adminUserService.setAdmin(user));
    }

}
