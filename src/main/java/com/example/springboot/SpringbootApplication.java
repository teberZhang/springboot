package com.example.springboot;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@MapperScan("com.example.springboot.orm.mapper") //扫描的mapper
@SpringBootApplication
public class SpringbootApplication {

    private static final Log log = LogFactory.get(SpringbootApplication.class);

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static void main(String[] args) {
        log.info("{}开始启动...", log.getName());
        SpringApplication.run(SpringbootApplication.class, args);
        log.info("{}启动成功！", log.getName());
    }

}
