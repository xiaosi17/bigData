package com.example.ecommerce.controller;

import com.example.ecommerce.aspect.RequireLogin;
import com.example.ecommerce.config.BusinessException;
import com.example.ecommerce.config.ErrorCode;
import com.example.ecommerce.dto.OrderItemRequest;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @RequireLogin
    public ResponseEntity<?> createOrder(
            @RequestHeader("UserId") Long userId,
            @Valid @RequestBody List<OrderItemRequest> items) {

        if (items.isEmpty()) {
            throw new BusinessException(ErrorCode.ORDER_ERROR.getCode(),
                    ErrorCode.ORDER_ERROR.getDesc());
        }

        Order order = orderService.createOrder(userId,items);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}



