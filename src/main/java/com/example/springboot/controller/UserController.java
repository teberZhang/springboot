package com.example.springboot.controller;

import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectUserById", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(User user) {
        return userService.sel(user);
    }

    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String add(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String update(User user) {
        return  userService.update(user);
    }

    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String delete(User user) {
        return userService.delete(user);
    }

    // 自定义注解验证数据不管
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @RequestBody UserAddForm userAddForm) {
        return userService.userAdd(userAddForm);
    }
}
