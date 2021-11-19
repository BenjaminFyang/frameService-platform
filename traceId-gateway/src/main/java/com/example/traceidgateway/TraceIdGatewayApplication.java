package com.example.traceidgateway;

import com.central.common.lb.annotation.EnableBaseFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableBaseFeignInterceptor
@EnableDiscoveryClient
@SpringBootApplication
public class TraceIdGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraceIdGatewayApplication.class, args);
    }


}
