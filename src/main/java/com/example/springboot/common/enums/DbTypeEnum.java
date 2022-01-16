package com.example.springboot.common.enums;

public enum DbTypeEnum {

    MASTER("master"), SLAVE("slave");
    private String value;

    DbTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
