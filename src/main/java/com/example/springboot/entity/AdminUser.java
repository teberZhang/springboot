package com.example.springboot.entity;

import lombok.Data;

@Data
public class AdminUser {

    private Integer id;

    private String name;

    private String password;

    private String type;
}
