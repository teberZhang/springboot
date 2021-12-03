package com.example.springboot.common.annotation.log;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringLogs {
    String serviceType() default "";
    String description() default "";
}