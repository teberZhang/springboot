package com.example.springboot.orm.mapper.slave;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springboot.orm.entity.slave.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
@DS(value = "slave")
public interface OrderMapper {
    Order Sel(@Param("order") Order order);

    int Add(@Param("order") Order order);

    int Update(@Param("order") Order order);

    int Delete(@Param("order") Order order);
}
