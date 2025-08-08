package com.example.ecommerce.service;

import com.example.ecommerce.config.BusinessException;
import com.example.ecommerce.config.ErrorCode;
import com.example.ecommerce.dto.OrderItemRequest;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.mapper.OrderItemMapper;
import com.example.ecommerce.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ProductService productService;


    private final Map<Long, List<OrderItem>> orderItemMap = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public void addItem(Long userId, OrderItem orderItem) {
        lock.lock();
        try {
            orderItemMap.computeIfAbsent(userId, k -> new ArrayList<>())
                    .add(orderItem);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 创建订单并处理商品项和库存扣减。
     */
    @Transactional
    public Order createOrder(Long userId, List<OrderItemRequest> itemRequests) {


        // 2. 创建订单对象
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(0); // 待支付状态

        // 3. 处理商品项
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : itemRequests) {
            Product product = productService.getProductById(itemRequest.getProductId());

            // 校验库存
            if (product.getStock() < itemRequest.getQuantity()) {
                throw new BusinessException(ErrorCode.STOCK_SHORTAGE.getCode(), "商品库存不足: " + product.getName());
            }

            // 创建订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);
            addItem(userId, orderItem);
            // 计算总金额
            totalAmount = totalAmount.add(
                    product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()))
            );
        }

        // 4. 保存订单
        order.setTotalAmount(totalAmount);
        orderMapper.insertOrder(order);

        // 5. 保存订单项（批量插入）
        for (List<OrderItem> items : orderItemMap.values()) {
            orderItems.addAll(items);
        }

        orderItems.forEach(item -> item.setOrderId(order.getId()));
        orderItemMapper.batchInsert(orderItems);

        // 6. 扣减库存（使用乐观锁）
        orderItems.forEach(item ->
                productService.deductStock(item.getProductId(), item.getQuantity())
        );

        order.setItems(orderItems);
        return order;
    }
}

