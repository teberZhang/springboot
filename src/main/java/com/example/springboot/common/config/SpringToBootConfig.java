package com.example.springboot.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "spring-to-boot")
@Component
public class SpringToBootConfig {
    /**
     * 数据同步时间
     */
    private Integer syncDayBefore;

    /**
     * 数据同步时间 描述
     */
    private String syncDayBeforeDesc;

    /**
     * token 有效期
     */
    private Integer tokenExpireHour;
}
