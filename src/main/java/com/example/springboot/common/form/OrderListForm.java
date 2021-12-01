package com.example.springboot.common.form;

import lombok.Data;

@Data
public class OrderListForm  extends BaseForm {

    private String status;

    private Integer user_id;
}
