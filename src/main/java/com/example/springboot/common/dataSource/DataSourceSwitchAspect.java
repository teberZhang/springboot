package com.example.springboot.common.dataSource;

import com.example.springboot.common.enums.DbTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@org.springframework.core.annotation.Order(-100)
@Slf4j
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.example.springboot.orm.mapper.master..*.*(..)) || execution(* com.example.springboot.orm.service.master..*.*(..))")
    private void masterAspect() {
    }

    @Pointcut("execution(* com.example.springboot.orm.mapper.slave..*.*(..)) || execution(* com.example.springboot.orm.service.slave..*.*(..))")
    private void slaveAspect() {
    }

    @Before("masterAspect()")
    public void master() {
        DbContextHolder.setDbType(DbTypeEnum.MASTER);
    }

    @Before("slaveAspect()")
    public void slave() {
        DbContextHolder.setDbType(DbTypeEnum.SLAVE);
    }
}
