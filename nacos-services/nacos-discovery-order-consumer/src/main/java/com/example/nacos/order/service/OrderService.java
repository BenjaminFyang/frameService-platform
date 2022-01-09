package com.example.nacos.order.service;


import com.example.nacos.order.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
