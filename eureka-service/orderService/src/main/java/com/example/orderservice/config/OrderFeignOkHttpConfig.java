package com.example.orderservice.config;

import com.example.feignokhttp.FeignOkHttpConfig;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OrderFeignOkHttpConfig extends FeignOkHttpConfig {


}
