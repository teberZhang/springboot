package com.example.springboot.controller;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.LoginUser;
import com.example.springboot.common.annotation.NeedAuth;
import com.example.springboot.common.annotation.PassToken;
import com.example.springboot.common.dto.AdminUserDTO;
import com.example.springboot.common.dto.LoginDTO;
import com.example.springboot.common.form.AdminUserListForm;
import com.example.springboot.common.form.ModifyPasswordForm;
import com.example.springboot.entity.AdminUser;
import com.example.springboot.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private AdminUserService adminUserService;

    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult<LoginDTO> login(@RequestBody AdminUser user) {

        return adminUserService.login(user);
    }

    @NeedAuth
    @RequestMapping(value = "/modify-password", method = RequestMethod.POST)
    public BaseResult<?> modifyPassword(@LoginUser AdminUser user, @Valid @RequestBody ModifyPasswordForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseResult.errorMsg("请正确填写密码");
        }

        return adminUserService.modifyPassword(user, form);
    }

    @NeedAuth
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResult<AdminUserDTO> list(@RequestBody AdminUserListForm form) {

        return adminUserService.list(form);
    }

}
