package com.example.alibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
        Thread.sleep(200L);
        return CommonResult.success("sleep!");
    }

    // 测试热点数据限流
    @GetMapping("/orderInfo")
    @SentinelResource("orderInfo")
    public CommonResult<String> orderInfo(Integer id) {
        return CommonResult.success("订单编号：" + id);
    }

}
