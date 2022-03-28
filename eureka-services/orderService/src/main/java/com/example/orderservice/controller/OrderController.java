package com.example.orderservice.controller;

import com.central.common.api.CommonResult;
import com.example.orderservice.domain.Order;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.spring.QlExpressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private QlExpressUtil qlExpressUtil;

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


    @RequestMapping("/qle")
    public CommonResult<String> test() throws Exception {

        // 数据开始处理.
        String express = ""
                + "if(userServiceImpl.get(nick)!=null) {"
                + "    userDO = userServiceImpl.get(nick);"
                + "    if(userDO.salary>20000 && userDO.salary<20000) {"
                + "        System.out.println('高级客户:'+userDO.nick);"
                + "    return '规则引擎该用户是高级客户哈';"
                + "    } else if(userDO.salary>=200000) {"
                + "        System.out.println('vip客户:'+userDO.nick);"
                + "    return '规则引擎该用户是VIP用户哈';"
                + "    } else {"
                + "        System.out.println('普通客户:'+userDO.nick);"
                + "    return '规则引擎该用户是普通用户';"
                + "    }"
                + "} else {"
                + "    System.out.println('用户信息不存在');"
                + "    return '查询不到用户信息';"
                + "}";

        Map<String, Object> context = new HashMap<>();
        context.put("nick", "马总");
        Object execute2 = qlExpressUtil.execute(express, context);
        log.info(execute2.toString());

        context.put("nick", "小王");
        Object execute = qlExpressUtil.execute(express, context);
        log.info(execute.toString());

        context.put("nick", "XXX");
        Object execute1 = qlExpressUtil.execute(express, context);
        log.info(execute1.toString());


        return CommonResult.success("规则引擎");
    }


    @RequestMapping("/qle2")
    public CommonResult<String> test2() throws Exception {

        // 规则表达式处理.
        String express = ""
                + "userDO = 读取用户信息(nick);"
                + "if(userDO != null) {"
                + "    if(判定用户是否vip)"
                + "        System.out.println('vip客户:' + nick);"
                + "} else {"
                + "    System.out.println('用户信息不存在，nick:' + nick);"
                + "}";

        Map<String, Object> context = new HashMap<>();

        context.put("nick", "马总");
        qlExpressUtil.execute(express, context);

        context.put("nick", "小王");
        qlExpressUtil.execute(express, context);

        context.put("nick", "XXX");
        qlExpressUtil.execute(express, context);

        return CommonResult.success("规则引擎执行的方法.");
    }

}
