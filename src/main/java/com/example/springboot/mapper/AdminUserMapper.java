package com.example.springboot.mapper;

import com.example.springboot.common.form.AdminUserListForm;
import com.example.springboot.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminUserMapper {
    AdminUser Sel(@Param("adminUser") AdminUser adminUser);

    int UpdateById(@Param("adminUser") AdminUser adminUser);

    List<AdminUser> SelectAllAdminUserByPageHelper(@Param("queryParam") AdminUserListForm adminUserListForm);
}
