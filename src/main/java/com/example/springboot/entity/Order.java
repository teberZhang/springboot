package com.example.springboot.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
public class Order {

    private BigInteger order_id;

    private String order_sn;

    private Integer doctor_id;

    private Integer user_id;

    private BigDecimal total_fee;

    private String status;

    private Date create_at;

    private Date update_at;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_sn='" + order_sn + '\'' +
                ", doctor_id=" + doctor_id +
                ", user_id=" + user_id +
                ", total_fee=" + total_fee +
                ", status='" + status + '\'' +
                ", create_at=" + create_at +
                ", update_at=" + update_at +
                '}';
    }
}
