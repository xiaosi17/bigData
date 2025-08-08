package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    Order selectOrderById(Long orderId);
}

