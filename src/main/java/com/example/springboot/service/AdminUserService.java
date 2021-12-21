package com.example.springboot.service;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.dto.LoginDTO;
import com.example.springboot.common.form.AdminUserListForm;
import com.example.springboot.common.form.ModifyPasswordForm;
import com.example.springboot.entity.AdminUser;

import java.util.List;

public interface AdminUserService {

    BaseResult<LoginDTO> login(AdminUser adminUser);

    BaseResult<?> modifyPassword(AdminUser adminUser, ModifyPasswordForm form);

    List<AdminUser> list(AdminUserListForm form);

    AdminUser findUserById(int id);

    AdminUser getAdmin(AdminUser adminUser);

    boolean setAdmin(AdminUser adminUser);
}
