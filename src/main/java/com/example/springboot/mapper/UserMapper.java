package com.example.springboot.mapper;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User Sel(@Param("user") User user);

    int Add(@Param("user") User user);

    int Update(@Param("user") User user);

    int Delete(@Param("user") User user);
}
