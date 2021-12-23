package com.example.springboot.common.vo;

import com.example.springboot.entity.AdminUser;
import lombok.Data;

import java.util.List;

@Data
public class CommonPageVo {
    private Integer page;
    private Integer size;
    private Long total;
    private List<AdminUser> list;
}
