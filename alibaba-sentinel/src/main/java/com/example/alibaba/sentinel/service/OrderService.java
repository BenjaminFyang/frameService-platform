package com.example.alibaba.sentinel.service;


import com.example.alibaba.sentinel.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
