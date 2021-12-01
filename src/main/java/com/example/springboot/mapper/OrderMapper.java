package com.example.springboot.mapper;

import com.example.springboot.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    Order Sel(@Param("order") Order order);

    int Add(@Param("order") Order order);

    int Update(@Param("order") Order order);

    int Delete(@Param("order") Order order);
}
