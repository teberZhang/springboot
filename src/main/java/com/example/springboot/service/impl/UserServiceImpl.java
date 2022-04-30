package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.orm.entity.master.User;
import com.example.springboot.orm.mapper.master.UserMapper;
import com.example.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    final Base64.Decoder decoder = Base64.getDecoder();

    @Override
    public User getUserInfo(User user) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("id", user.getId()));
    }

    @Override
    public Integer deleteUser(User user) {
       return userMapper.deleteById(user.getId());
    }

    @Override
    public Integer updateUser(User user) {
        log.info("修改用户信息", user);
        return userMapper.update(user, new QueryWrapper<User>().eq("id", user.getId()));
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer userAdd(UserAddForm userAddForm) {
        User user = new User();
        BeanUtil.copyProperties(user, userAddForm);
        return userMapper.insert(user);
    }
}
