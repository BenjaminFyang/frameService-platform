package com.example.traceideureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class TraceIdEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraceIdEurekaApplication.class, args);
    }

}
