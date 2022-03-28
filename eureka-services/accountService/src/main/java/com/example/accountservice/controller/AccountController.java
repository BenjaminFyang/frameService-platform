package com.example.accountservice.controller;

import com.central.common.api.CommonResult;
import com.example.accountservice.spring.QlExpressUtil;
import com.example.accountservice.spring.UserDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.accountservice.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @Resource
    private QlExpressUtil qlExpressUtil;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/decrease")
    public CommonResult<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return CommonResult.success("扣减账户余额成功！");
    }


    @RequestMapping("/qle")
    public CommonResult<String> test() throws Exception {

        UserDO userDO = new UserDO();
        userDO.setNick("方洋");
        userDO.setGmtCreate(new Date());
        userDO.setUserId(10086L);
        userDO.setPosition("salesman");
        userDO.setSalary(5000.0);

        // 数据开始处理.
        String express = ""
                + "if(userDO!=null) {"
//                + "    userDO = bizLogicBean.getUserInfo(nick);"
                + "    if(userDO.salary>20000 && userDO.salary<20000) {"
                + "        System.out.println('高级客户:'+userDO.nick);"
                + "    } else if(userDO.salary>=200000) {"
                + "        System.out.println('vip客户:'+userDO.nick);"
                + "    return 333"
                + "    } else {"
                + "        System.out.println('普通客户:'+userDO.nick);"
                + "    return 333"
                + "    }"
                + "} else {"
                + "    System.out.println('用户信息不存在');"
                + "}";

        Map<String, Object> context = new HashMap<>();
        context.put("nick", "马总");
        qlExpressUtil.execute(express, context);

        context.put("nick", "小王");
        Object execute = qlExpressUtil.execute(express, context);
        System.out.println(execute + "777");


        context.put("nick", "XXX");
        qlExpressUtil.execute(express, context);

        return CommonResult.success("规则引擎");
    }

}
