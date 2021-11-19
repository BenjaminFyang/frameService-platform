package com.example.orderservice.service;


import com.example.orderservice.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
