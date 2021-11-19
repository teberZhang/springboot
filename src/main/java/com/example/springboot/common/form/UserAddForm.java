package com.example.springboot.common.form;

import com.example.springboot.common.annotation.AgeOdd;
import lombok.Data;

@Data
public class UserAddForm {

    @AgeOdd
    private Integer age;
    private String username;
    private String password;
    private String address;
}
