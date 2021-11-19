package com.example.springboot.common.annotation;

import com.example.springboot.common.validator.AgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 注解验证器
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeOdd {
    boolean required() default true;
    String message() default "年龄必须必须是奇数";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
