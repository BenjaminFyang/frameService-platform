package com.example.orderservice.service.fallback;

import com.central.common.api.CommonResult;
import com.example.orderservice.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * StorageService降级工场
 */
@Slf4j
public class StorageServiceServiceFallbackFactory implements FallbackFactory<StorageService> {

    @Override
    public StorageService create(Throwable throwable) {
        return (productId, count) -> {
            log.error("调用库存服务扣减商品id:{},数量:{}异常", productId, count);
            return CommonResult.failed("扣减库存异常");
        };
    }
}
