package com.example.netflix.hystrix.controller;

import com.example.netflix.hystrix.service.CacheService;
import com.example.netflix.hystrix.service.CollapserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CacheService cacheService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CollapserService collapserService;


    @GetMapping("/get_user")
    @HystrixCommand(fallbackMethod = "getUserFallback")
    public String getUser1(@RequestParam("id") Integer id) {
        logger.info("[getUser][准备调用 user-service 获取用户({})详情", id);
        return restTemplate.getForEntity("http://127.0.0.1:8080/user/get?id=" + id, String.class).getBody();
    }

    public String getUserFallback(Integer id, Throwable throwable) {
        logger.info("getUserFallback][id({}) exception({})", id, ExceptionUtils.getRootCauseMessage(throwable));
        return "mock:User:" + id;
    }


    @GetMapping("/cache/get_user")
    public String getUser2(@RequestParam("id") Integer id) {
        String userA = cacheService.getUser(id);
        String userB = cacheService.getUser(id);
        return cacheService.getUser(id);
    }

    @GetMapping("/cache/update_user")
    public String updateUser(@RequestParam("id") Integer id) {
        String userA = cacheService.getUser(id);
        cacheService.updateUser(id);
        return cacheService.getUser(id);
    }


    @GetMapping("/test")
    public void test() throws ExecutionException, InterruptedException {
        logger.info("准备获取用户信息");
        Future<String> user01 = collapserService.getUserFuture(1);
        Future<String> user02 = collapserService.getUserFuture(2);
        logger.info("提交获取用户信息");

        logger.info("user({}) 的结果为({})", 1, user01.get());
        logger.info("user({}) 的结果为({})", 2, user02.get());
    }


    @GetMapping("/test02")
    public void test02() throws ExecutionException, InterruptedException {
        logger.info("准备获取用户信息");
        Future<String> user01 = collapserService.getUserFuture(2);
        Future<String> user02 = collapserService.getUserFuture(1);
        logger.info("提交获取用户信息");

        logger.info("user({}) 的结果为({})", 1, user01.get());
        logger.info("user({}) 的结果为({})", 2, user02.get());
    }

}
