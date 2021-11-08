package com.example.springboot.service;

import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
}
