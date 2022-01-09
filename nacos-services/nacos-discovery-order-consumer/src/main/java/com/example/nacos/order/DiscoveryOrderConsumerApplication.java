package com.example.nacos.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class DiscoveryOrderConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryOrderConsumerApplication.class, args);
    }


}
