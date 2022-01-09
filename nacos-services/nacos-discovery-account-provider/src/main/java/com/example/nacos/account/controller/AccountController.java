package com.example.nacos.account.controller;

import com.central.common.api.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    /**
     * 扣减账户余额
     */
    @RequestMapping("/decrease")
    public CommonResult<String> decrease() {
        return CommonResult.success("扣减账户余额成功！");
    }
}
