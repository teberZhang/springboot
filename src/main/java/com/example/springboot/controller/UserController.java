package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.orm.entity.master.User;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/selectUserById", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    @SpringLogs(serviceType="UserController.getUser", description="获取用户基础信息")
    public BaseResult<?> getUser(User user) {
        return BaseResult.ok(userService.getUserInfo(user));
    }

    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @SpringLogs(serviceType="UserController.add", description="新增用户信息")
    public BaseResult<?> add(@Valid @RequestBody User user) {
        return BaseResult.ok(userService.addUser(user));
    }

    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @SpringLogs(serviceType="UserController.update", description="修改用户信息")
    public BaseResult<?> update(User user) {
        return  BaseResult.ok(userService.updateUser(user));
    }

    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @SpringLogs(serviceType="UserController.delete", description="删除用户信息")
    public BaseResult<?> delete(User user) {
        return BaseResult.ok(userService.deleteUser(user));
    }

    // 自定义注解验证数据不管
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @SpringLogs(serviceType="UserController.addUser", description="新增用户信息")
    public BaseResult<?> addUser(@Valid @RequestBody UserAddForm userAddForm) {
        return BaseResult.ok(userService.userAdd(userAddForm));
    }
}
