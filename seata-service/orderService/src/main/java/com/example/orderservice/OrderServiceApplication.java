package com.example.orderservice;

import com.example.traceIdRocketmq.annotation.EnableHyhRocketMq;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHyhRocketMq
@MapperScan("com.example.orderservice.mapper")
public class OrderServiceApplication {

    public static void main(String[] args) {
//        ElasticApmAttacher.attach();
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
