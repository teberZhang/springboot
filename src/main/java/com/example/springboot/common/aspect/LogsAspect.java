package com.example.springboot.common.aspect;

import com.example.springboot.common.constant.AspectOrderConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志切面 设置为高优先级-101
 */
@Slf4j
@Aspect
@Component
@Order(AspectOrderConstants.LogsAspectOrder)
public class LogsAspect {
}
