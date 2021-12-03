package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.CommonPage;
import com.example.springboot.common.annotation.LoginUser;
import com.example.springboot.common.annotation.NeedAuth;
import com.example.springboot.common.annotation.PassToken;
import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.common.dto.LoginDTO;
import com.example.springboot.common.form.AdminUserListForm;
import com.example.springboot.common.form.ModifyPasswordForm;
import com.example.springboot.entity.AdminUser;
import com.example.springboot.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private AdminUserService adminUserService;

    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @SpringLogs(serviceType="UserAuthController.login", description="用户登录")
    public BaseResult<LoginDTO> login(@RequestBody AdminUser user) {

        return adminUserService.login(user);
    }

    @NeedAuth
    @RequestMapping(value = "/modify-password", method = RequestMethod.POST)
    @SpringLogs(serviceType="UserAuthController.modifyPassword", description="用户修改密码")
    public BaseResult<?> modifyPassword(@LoginUser AdminUser user, @Valid @RequestBody ModifyPasswordForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseResult.errorMsg("请正确填写密码");
        }

        return adminUserService.modifyPassword(user, form);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @SpringLogs(serviceType="UserAuthController.list", description="用户列表获取")
    public BaseResult<CommonPage<AdminUser>> list(AdminUserListForm form) {

        List<AdminUser> adminUserList =  adminUserService.list(form);
        return BaseResult.ok(CommonPage.restPage(adminUserList));
    }

}
