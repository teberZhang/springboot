package com.example.springboot.common.validator;

import com.example.springboot.common.annotation.AgeOdd;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AgeValidator implements ConstraintValidator<AgeOdd, Integer> {

    @Override
    public void initialize(AgeOdd constraintAnnotation) {
        // 这里可以获取注解里面的一些参数
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
        return age % 2 != 0;
    }
}
