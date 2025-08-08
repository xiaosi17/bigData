package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    private Long id;
    private Long userId;
    private BigDecimal totalAmount;
    private Integer status;
    private Date createTime;

//    // 订单项列表（非数据库字段）
//    @Transient
    private List<OrderItem> items;
}
