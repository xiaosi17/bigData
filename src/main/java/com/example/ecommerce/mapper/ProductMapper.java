package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product selectById(Long id);


    int insert(Product product);

    List<Product> selectAll();
    // 更新库存（XML映射方式）
    int updateStock(@Param("id") Long id, @Param("count") Integer count);

    // Mapper实现
    int deductStock(@Param("productId") Long productId,
                    @Param("quantity") Integer quantity);
}

