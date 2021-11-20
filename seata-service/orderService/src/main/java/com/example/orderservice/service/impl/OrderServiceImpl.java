package com.example.orderservice.service.impl;

import com.example.orderservice.domain.Order;
import com.example.orderservice.service.AccountService;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.StorageService;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * 订单业务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Resource(name = "consumerQueueThreadPool")
    private ExecutorService executorService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    @Override
    public void create(Order order) {

        List<Order> list = Lists.newArrayList();
        list.add(order);
        list.add(order);
        list.add(order);

        List<CompletableFuture<Integer>> completableFutureList = list.stream()
                .map(or -> CompletableFuture.supplyAsync(() -> {
                    LOGGER.info("11下单异步数据统计");
                    return 22;
                }, executorService))
                .collect(Collectors.toList());

        List<Integer> integerList = completableFutureList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        LOGGER.info("integerList={}", integerList);

        executorService.submit(() -> {
            LOGGER.info("下单异步数据统计.");
        });


        LOGGER.info("------->下单开始");
        //本应用创建订单
        LOGGER.info("------->order-service中创建订单");

        //远程调用库存服务扣减库存
        LOGGER.info("------->order-service中扣减库存开始");
        storageService.decrease(order.getProductId(), order.getCount());
        LOGGER.info("------->order-service中扣减库存结束");

        //远程调用账户服务扣减余额
        LOGGER.info("------->order-service中扣减余额开始");
        accountService.decrease(order.getUserId(), order.getMoney());
        LOGGER.info("------->order-service中扣减余额结束");

        //修改订单状态为已完成
        LOGGER.info("------->order-service中修改订单状态开始");

        LOGGER.info("------->order-service中修改订单状态中");

        LOGGER.info("------->order-service中修改订单状态结束");

        LOGGER.info("------->下单结束");
    }
}
