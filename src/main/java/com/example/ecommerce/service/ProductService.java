package com.example.ecommerce.service;

import com.example.ecommerce.config.BusinessException;
import com.example.ecommerce.config.ErrorCode;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

//    @Cacheable(value = "product", key = "#id")
    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }

    public int createProduct(Product product) {
        return productMapper.insert(product);
    }

    // 查询所有商品
    public List<Product> getAllProducts() {
        return productMapper.selectAll();
    }

    // 扣减库存（带事务）
    @Transactional
    public boolean reduceStock(Long productId, Integer count) {
        int affectedRows = productMapper.updateStock(productId, count);
        return affectedRows > 0;
    }

    // 使用CAS（Compare And Set）实现乐观锁扣减
    public void deductStock(Long productId, Integer quantity) {
        int rows = productMapper.deductStock(productId, quantity);
        if (rows == 0) {
            throw new BusinessException(ErrorCode.STOCK_SHORTAGE.getCode(),"商品库存不足");
        }
    }



}
