package com.example.orderservice.controller;

import com.central.common.api.CommonResult;
import com.example.orderservice.domain.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public CommonResult<String> create(@RequestBody Order order) {
        orderService.create(order);

        int i = 1 / 0;
        System.out.println(111);

        System.out.println("222");
//        while (true) {
//            Order order1 = new Order();
//        }


        return CommonResult.success("订单创建成功!");
    }

}
