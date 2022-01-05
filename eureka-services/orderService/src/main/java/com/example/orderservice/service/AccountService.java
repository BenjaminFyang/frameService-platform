package com.example.orderservice.service;

import com.central.common.api.CommonResult;
import com.central.common.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = ServiceNameConstants.ACCOUNT_SERVICE)
public interface AccountService {

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    CommonResult<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
