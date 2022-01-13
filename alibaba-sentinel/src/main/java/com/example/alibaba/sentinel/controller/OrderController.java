package com.example.alibaba.sentinel.controller;

import com.central.common.api.CommonResult;

import com.example.alibaba.sentinel.domain.Order;
import com.example.alibaba.sentinel.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public CommonResult<String> create(@RequestBody Order order) {
        orderService.create(order);
        return CommonResult.success("订单创建成功!");
    }

    @GetMapping("/sleep")
    public CommonResult<String> sleep() throws InterruptedException {
        Thread.sleep(100L);
        return CommonResult.success("sleep!");
    }


}
