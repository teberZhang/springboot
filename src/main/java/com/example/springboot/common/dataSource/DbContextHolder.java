package com.example.springboot.common.dataSource;

import com.example.springboot.common.enums.DbTypeEnum;

public class DbContextHolder {

    private static final ThreadLocal contextHolder = new ThreadLocal<>();
    /**
     * 设置数据源
     * @param dbTypeEnum DbTypeEnum
     */
    public static void setDbType(DbTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum.getValue());
    }
    /**
     * 取得当前数据源
     * @return String
     */
    public static String getDbType() {
        return (String) contextHolder.get();
    }
    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
