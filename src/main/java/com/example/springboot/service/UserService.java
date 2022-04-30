package com.example.springboot.service;

import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.orm.entity.master.User;

public interface UserService {

    User sel(User user);

    String delete(User user);

    String update(User user);

    String add(User user);

    String userAdd(UserAddForm userAddForm);
}
