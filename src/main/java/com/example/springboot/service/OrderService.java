package com.example.springboot.service;

import com.example.springboot.common.form.UserAddForm;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public Order sel(Order order) {
        return orderMapper.Sel(order);
    }

    public String delete(Order order) {
        int result = orderMapper.Delete(order);
        if (result == 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    public String update(Order order) {
        int result = orderMapper.Update(order);
        if (result == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    public String add(Order order) {
        int result = orderMapper.Add(order);
        if (result == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    public String userAdd(UserAddForm userAddForm) {
        Order order = new Order();

        int result = orderMapper.Add(order);
        if (result == 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }
}
