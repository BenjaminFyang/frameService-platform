package com.example.shardingjdbc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shardingjdbc.dataobject.OrderDO;
import com.example.shardingjdbc.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


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
        System.out.println(exists);
    }

    public OrderDO findById(Integer id) {

        OrderDO order = new OrderDO();
        order.setUserId(20);
        add(order);

        // 数据库的读写分离、分库与分表
        Page<OrderDO> page = new Page<>(1, 3);
        IPage<OrderDO> iPage = orderMapper.selectPage(page, null);
        List<OrderDO> records = iPage.getRecords();

        return orderMapper.selectById(id);
    }

}
