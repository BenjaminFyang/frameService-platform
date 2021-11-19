package com.example.storageservice.controller;


import com.central.common.api.CommonResult;
import com.example.storageservice.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/decrease")
    public CommonResult<String> decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return CommonResult.success("扣减库存成功！");
    }
}
