package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.common.BaseResult;
import com.example.springboot.common.dto.LoginDTO;
import com.example.springboot.common.form.AdminUserListForm;
import com.example.springboot.common.form.ModifyPasswordForm;
import com.example.springboot.common.vo.CommonPageVo;
import com.example.springboot.entity.AdminUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author teber_zhang
 * @since 2021-12-22
 */
public interface IAdminUserService extends IService<AdminUser> {

    BaseResult<LoginDTO> login(AdminUser adminUser);

    BaseResult<?> modifyPassword(AdminUser adminUser, ModifyPasswordForm form);

    CommonPageVo adminUserList(AdminUserListForm form);
}