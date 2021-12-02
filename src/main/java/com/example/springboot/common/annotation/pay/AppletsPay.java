package com.example.springboot.common.annotation.pay;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppletsPay {
    boolean required() default true;
    String message() default "小程序支付";
}
