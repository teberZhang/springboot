package com.example.springboot.common.dto;

import lombok.Data;

@Data
public class AdminUserDTO {

    private Integer id;

    private String name;

    private String password;

    private String type;

}
