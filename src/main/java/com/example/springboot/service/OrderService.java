package com.example.springboot.service;

import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.orm.entity.slave.Order;

public interface OrderService {

    Order sel(Order order);

    String delete(Order order);

    String update(Order order);

    String add(Order order);

    String userAdd(UserAddForm userAddForm);
}
