package com.example.springboot.common.aspect;

import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.common.constant.AspectOrderConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@Order(AspectOrderConstants.LogsAspectOrder)
public class LogsAspect {

    private final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("@annotation(com.example.springboot.common.annotation.log.SpringLogs) ")
    public void cutController() {
    }

    /**
     * 日志切面 (异步插入)
     * @param joinPoint 切点
     * @param annotation 切入注解
     * @return 切入方法返回值
     * @throws Throwable 异常
     */
    @Around(value = "cutController() && @annotation(annotation)")
    public Object beforeService(ProceedingJoinPoint joinPoint, SpringLogs annotation) throws Throwable {



        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod(); // 获取切入的方法
        Object[] args = joinPoint.getArgs(); // 获取切入的参数

        // 打印请求相关参数
        logger.info("========================================== 日志Start ==========================================");
        // 打印请求 className
        logger.info("className            : {}", method.getDeclaringClass().getName());
        // 打印请求 methodName
        logger.info("methodName            : {}", method.getName());
        // 打印请求 serviceType
        logger.info("serviceType            : {}", annotation.serviceType());
        // 打印请求 description
        logger.info("description            : {}", annotation.description());

        try {
            Object res = joinPoint.proceed();
            return res;
        } catch (Throwable throwable) {
            log.error("记录错误日志！");
            throw throwable;
        }
    }
}
