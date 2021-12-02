package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 4286442241675639887L;

    private BigInteger orderId;

    private String orderSn;

    private Integer doctorId;

    private Integer userId;

    private BigDecimal totalFee;

    private String status;

    // 后台传递给前台
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // 前台传递给后端
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    // 后台传递给前台
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    // 前台传递给后端
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateAt;
}
