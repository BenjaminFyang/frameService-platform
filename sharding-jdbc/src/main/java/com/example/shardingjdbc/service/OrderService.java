package com.example.shardingjdbc.service;

import com.example.shardingjdbc.dataobject.OrderDO;
import com.example.shardingjdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Slf4j
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Transactional
    public void add(OrderDO order) {

        // 这里先假模假样的读取一下。读取从库
        OrderDO exists = orderMapper.selectById(1);
        System.out.println(exists);

        // 插入订单
        orderMapper.insert(order);

        // 这里先假模假样的读取一下。读取主库
        exists = orderMapper.selectById(1);
        log.info("[OrderService][对应的订单数据为order={}]", exists);
    }

    public OrderDO findById(Integer id) {
        OrderDO order = new OrderDO();
        order.setUserId(20);
        add(order);

        return orderMapper.selectById(id);
    }

}
