package com.example.springboot.common.dto;

import com.example.springboot.entity.AdminUser;
import lombok.Data;

import java.util.List;

@Data
public class AdminUserDTO {

    private List<AdminUser> list;

    private Long count;

    private Integer page;

    private Integer pageSize;

}
