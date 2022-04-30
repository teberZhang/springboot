package com.example.springboot.common.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 取得当前使用哪个数据源
     * @return Object
     */
    @Override
    protected Object determineCurrentLookupKey(){
        return DbContextHolder.getDbType();
    }
}
