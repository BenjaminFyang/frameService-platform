package com.example.alibaba.sentinel.service.impl;


import com.example.alibaba.sentinel.domain.Order;
import com.example.alibaba.sentinel.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 订单业务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    public void create(Order order) {

        LOGGER.info("------->下单开始");
        //本应用创建订单
        LOGGER.info("------->order-service中创建订单");

    }

}
