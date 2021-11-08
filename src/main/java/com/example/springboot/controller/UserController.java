package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String add(User user) {
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
}
