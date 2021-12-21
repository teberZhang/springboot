package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.entity.AdminUser;
import com.example.springboot.service.impl.AdminUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisOperateController {

    @Autowired
    private AdminUserServiceImpl adminUserService;

    @RequestMapping(value = "/getAdmin", method = RequestMethod.POST)
    @SpringLogs(serviceType="RedisOperateController.getAdmin", description="Redis操作:获取admin信息")
    public BaseResult<AdminUser> getAdmin(@RequestBody AdminUser user) {

        return BaseResult.ok(adminUserService.getAdmin(user));
    }

    @RequestMapping(value = "/setAdmin", method = RequestMethod.POST)
    @SpringLogs(serviceType="RedisOperateController.setAdmin", description="Redis操作:设置admin信息")
    public BaseResult<?> setAdmin(@RequestBody AdminUser user) {

        return BaseResult.ok(adminUserService.setAdmin(user));
    }

}
