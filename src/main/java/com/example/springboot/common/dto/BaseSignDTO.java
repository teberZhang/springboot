package com.example.springboot.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseSignDTO implements Serializable {

    private static final long serialVersionUID = -7550242465403464713L;

    private String appId;

    private String msgId;

    private String nonceStr;

    private Long reqTime;

    private String sign;
}
