package com.example.nacos.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DiscoveryAccountProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryAccountProviderApplication.class, args);
    }

}
