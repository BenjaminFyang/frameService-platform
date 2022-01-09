package com.example.nacos.order.service.impl;


import com.example.nacos.order.domain.Order;
import com.example.nacos.order.service.AccountService;
import com.example.nacos.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单业务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private AccountService accountService;


    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    public void create(Order order) {

        LOGGER.info("------->下单开始");
        //本应用创建订单
        LOGGER.info("------->order-service中创建订单");

        // 远程调用账户服务扣减余额
        LOGGER.info("------->order-service中扣减余额开始");
        accountService.decrease();
        LOGGER.info("------->order-service中扣减余额结束");
    }

}
