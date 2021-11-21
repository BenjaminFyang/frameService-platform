package com.example.traceIdRocketmq.config;


import com.example.traceIdRocketmq.template.RocketMQTemplateProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMq AutoConfiguration
 *
 * @author :fy
 */
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
public class RocketMqAutoConfiguration {

    @Bean
    public RocketMQTemplateProducer rocketMqHelper() {
        return new RocketMQTemplateProducer();
    }

}
