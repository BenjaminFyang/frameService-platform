package com.example.netflix.hystrix.service;

import com.netflix.hystrix.*;


/**
 * 可以对接第三方服务接口的限制、
 */
public class GetProductInfoCommand extends HystrixCommand<String> {

    private final Long cityId;

    private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetCityInfoCommand");

    public GetProductInfoCommand(Long cityId) {

        // 设置信号量隔离策略
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CityInfoService"))
                .andCommandKey(KEY)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(8)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(8))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        .withCircuitBreakerRequestVolumeThreshold(20)
                        .withCircuitBreakerErrorThresholdPercentage(40)
                        .withCircuitBreakerSleepWindowInMilliseconds(3000)
                        // 设置是否打开超时，默认是true
                        .withExecutionTimeoutEnabled(true)
                        // 设置超时时间，默认1000(ms)
                        .withExecutionTimeoutInMilliseconds(500)
                        // 设置降级机制最大并发请求数
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(30)));

        this.cityId = cityId;
    }

    @Override
    protected String run() {
        // 需要进行信号量隔离的代码
        return LocationCache.getCityName(cityId);
    }
}