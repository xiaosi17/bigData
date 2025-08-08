package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    int batchInsert(@Param("items") List<OrderItem> items);
}

