package com.example.nacosConfig.controller;

import com.central.common.api.CommonResult;
import com.example.nacosConfig.configProperties.OrderProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {

    @Resource
    private OrderProperties orderProperties;

    @PostMapping("/create")
    public CommonResult<String> create() {
        return CommonResult.success("订单创建成功!");
    }

    @PostMapping("/getOrderProperties")
    public CommonResult<OrderProperties> getOrderProperties() {
        return CommonResult.success(orderProperties);
    }

    @Value("${xxx-password:}")
    private String xxxPassword;

    @GetMapping("/test")
    public String test() {
        return xxxPassword;
    }

}
