package com.example.traceIdRocketmq.annotation;


import com.example.traceIdRocketmq.config.RocketMqAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启RocketMq注解
 *
 * @author :fy
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({RocketMqAutoConfiguration.class})
public @interface EnableHyhRocketMq {
}
