package com.example.orderservice.service;

import com.central.common.api.CommonResult;
import com.central.common.constant.ServiceNameConstants;
import com.example.orderservice.service.fallback.StorageServiceServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = ServiceNameConstants.STORAGE_SERVICE, fallbackFactory = StorageServiceServiceFallbackFactory.class, decode404 = true)
public interface StorageService {

    /**
     * 扣减库存
     *
     * @param productId 对应商品的id.
     * @param count     扣减库存的数量.
     * @return the CommonResult
     */
    @GetMapping(value = "/storage/decrease")
    CommonResult<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
