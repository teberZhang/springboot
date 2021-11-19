package com.example.springboot.mapper;

import com.example.springboot.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminUserMapper {
    AdminUser Sel(@Param("adminUser") AdminUser adminUser);

    int UpdateById(@Param("adminUser") AdminUser adminUser);
}
