package com.example.nacosConfig.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class EnvironmentChangeListener implements ApplicationListener<EnvironmentChangeEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ConfigurableEnvironment environment;

    public void onApplicationEvent(EnvironmentChangeEvent environmentChangeEvent) {
        for (String key : environmentChangeEvent.getKeys()) {
            logger.info("[onApplicationEvent][key({}) 最新 value 为 {}]", key, environment.getProperty(key));
        }
    }
}
