package com.example.springboot.common.form;

import lombok.Data;

@Data
public class AdminUserListForm extends BaseForm {

    private String name;

    private String type;
}
