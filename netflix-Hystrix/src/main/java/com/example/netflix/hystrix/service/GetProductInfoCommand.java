package com.example.netflix.hystrix.service;

import com.netflix.hystrix.*;


/**
 * 第三方服务接口的限制可以使用线程池进行隔离
 * 内部系统没有timeOut超时的机制，使用信号量隔离即可.
 */
public class GetProductInfoCommand extends HystrixCommand<String> {

    private final Long cityId;

    private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetCityInfoCommand");

    public GetProductInfoCommand(Long cityId) {

        // 线程池隔离+断路器机制.
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CityInfoService"))
                .andCommandKey(KEY)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(8)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(8))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 是否允许断路器工作
                        .withCircuitBreakerEnabled(true)
                        // 滑动窗口中，最少有多少个请求，才可能触发断路
                        .withCircuitBreakerRequestVolumeThreshold(20)
                        // 异常比例达到多少，才触发断路，默认50%
                        .withCircuitBreakerErrorThresholdPercentage(40)
                        // 断路后多少时间内直接reject请求，之后进入half-open状态，默认5000ms
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