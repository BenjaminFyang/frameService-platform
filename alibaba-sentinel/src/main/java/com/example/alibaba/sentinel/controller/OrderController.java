package com.example.alibaba.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.central.common.api.CommonResult;

import com.central.common.exception.BusinessException;
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

    // 测试「Sentinel 客户端 API」
    @GetMapping("/createOne")
    public CommonResult<String> entryDemo() {
        Entry entry = null;
        try {
            // <1> 访问资源
            entry = SphU.entry("createOne");

            // <2> ... 执行业务逻辑
            return CommonResult.success("执行成功");
        } catch (BlockException ex) { // <3>
            return CommonResult.success("被拒绝");
        } finally {
            // <4> 释放资源
            if (entry != null) {
                entry.exit();
            }
        }
    }

    // 测试「Sentinel @SentinelResource 注解」
    @GetMapping("/annotations")
    @SentinelResource(value = "annotations", blockHandler = "blockHandler", fallback = "fallback")
    public CommonResult<String> annotationsDemo(@RequestParam(required = false) Integer id) {
        if (id == null) {
            throw new BusinessException("id 参数不允许为空");
        }
        return CommonResult.success("success...");
    }

    // BlockHandler 处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public CommonResult<String> blockHandler(Integer id, BlockException ex) {
        return CommonResult.success("block：" + ex.getClass().getSimpleName());
    }

    // Fallback 处理函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public CommonResult<String> fallback(Integer id, Throwable throwable) {
        return CommonResult.success("fallback：" + throwable.getMessage());
    }

}
