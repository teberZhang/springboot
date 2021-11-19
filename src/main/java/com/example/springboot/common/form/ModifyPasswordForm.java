package com.example.springboot.common.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ModifyPasswordForm {
    @NotBlank(message = "请填写原密码")
    private String oldPassword;

    @NotBlank(message = "请填写新密码")
    private String newPassword;

    @NotBlank(message = "请填写新密码")
    private String rePassword;
}
