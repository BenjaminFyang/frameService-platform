package com.example.nacosConfig.controller;

import com.central.common.api.CommonResult;
import com.example.nacosConfig.configProperties.OrderProperties;
import com.example.nacosConfig.domain.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderProperties orderProperties;

    @PostMapping("/create")
    public CommonResult<String> create(@RequestBody Order order) {
        return CommonResult.success("订单创建成功!");
    }

    @PostMapping("/getOrderProperties")
    public CommonResult<OrderProperties> getOrderProperties() {
        return CommonResult.success(orderProperties);
    }
}
