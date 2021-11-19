package com.example.springboot.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private Integer age;
    private String username;
    private String password;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
