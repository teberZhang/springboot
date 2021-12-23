package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.entity.User;

public interface IUserService extends IService<User> {

    User getUserInfo(User user);

    Integer deleteUser(User user);

    Integer updateUser(User user);

    Integer addUser(User user);

    Integer userAdd(UserAddForm userAddForm);
}
