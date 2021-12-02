package com.example.springboot.common.constant;

/**
 * 切面排序常量
 * 值越小优先级越高
 */
public class AspectOrderConstants {

    // 签名切面
    public static final int VerifySignAspectOrder = -99;
    // 日志切面
    public static final int LogsAspectOrder = -101;
    // 控制器切面
    public static final int ControllerAspectOrder = -103;

}
