package com.example.netflix.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CollapserService {

    private final Logger logger = LoggerFactory.getLogger(CollapserService.class);

    @Resource
    private RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "getUsers", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "10000")})
    public Future<String> getUserFuture(Integer id) {
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand
    public List<String> getUsers(List<Integer> ids) {
        logger.info("[getUsers][准备调用 user-service 获取多个用户({})详情]", ids);
        String[] users = restTemplate.getForEntity("http://127.0.0.1:8080/user/batch_get?ids=" + StringUtils.join(ids, ','), String[].class).getBody();
        return users == null || users.length == 0 ? Collections.emptyList() : Arrays.asList(users);
    }

}
