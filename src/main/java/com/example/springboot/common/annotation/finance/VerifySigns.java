package com.example.springboot.common.annotation.finance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface VerifySigns {
    boolean required() default true;
    String message() default "财务异步回调验签";
}
