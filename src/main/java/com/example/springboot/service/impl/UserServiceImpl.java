package com.example.springboot.service.impl;

import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User sel(User user) {
        return userMapper.Sel(user);
    }

    public String delete(User user) {
        int result = userMapper.Delete(user);
        if (result == 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    public String update(User user) {
        int result = userMapper.Update(user);
        if (result == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    public String add(User user) {
        int result = userMapper.Add(user);
        if (result == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    public String userAdd(UserAddForm userAddForm) {
        User user = new User();
        user.setAge(userAddForm.getAge());
        user.setPassword(userAddForm.getPassword());
        user.setUsername(userAddForm.getUsername());
        user.setAddress(userAddForm.getAddress());
        int result = userMapper.Add(user);
        if (result == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }
}
