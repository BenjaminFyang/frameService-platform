//package com.central.common.utils;
//
//
//import feign.Feign;
//import okhttp3.ConnectionPool;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
//import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
//import org.springframework.cloud.openfeign.FeignClientProperties;
//import org.springframework.cloud.openfeign.loadbalancer.FeignLoadBalancerAutoConfiguration;
//import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PreDestroy;
//import javax.annotation.Resource;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@ConditionalOnClass(Feign.class)
//@AutoConfigureBefore(FeignLoadBalancerAutoConfiguration.class)
//public class FeignOkHttpConfig {
//
//
//    @Resource
//    private OkHttpResponseInterceptor okHttpResponseInterceptor;
//
//    private okhttp3.OkHttpClient okHttpClient;
//
//
//    @Bean
//    @ConditionalOnMissingBean(ConnectionPool.class)
//    public ConnectionPool httpClientConnectionPool(FeignHttpClientProperties httpClientProperties,
//                                                   OkHttpClientConnectionPoolFactory connectionPoolFactory) {
//        int maxTotalConnections = httpClientProperties.getMaxConnections();
//        long timeToLive = httpClientProperties.getTimeToLive();
//        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
//        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
//    public okhttp3.OkHttpClient okHttpClient(OkHttpClientFactory httpClientFactory, ConnectionPool connectionPool, FeignClientProperties feignClientProperties, FeignHttpClientProperties feignHttpClientProperties) {
//        FeignClientProperties.FeignClientConfiguration defaultConfig = feignClientProperties.getConfig().get("default");
//        int connectTimeout = feignHttpClientProperties.getConnectionTimeout();
//        int readTimeout = defaultConfig.getReadTimeout();
//        boolean disableSslValidation = feignHttpClientProperties.isDisableSslValidation();
//        boolean followRedirects = feignHttpClientProperties.isFollowRedirects();
//        this.okHttpClient = httpClientFactory.createBuilder(disableSslValidation)
//                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
//                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
//                .followRedirects(followRedirects)
//                .connectionPool(connectionPool)
//                .addInterceptor(okHttpResponseInterceptor)
//                .build();
//        return this.okHttpClient;
//    }
//
//
//    @PreDestroy
//    public void destroy() {
//        if (this.okHttpClient != null) {
//            this.okHttpClient.dispatcher().executorService().shutdown();
//            this.okHttpClient.connectionPool().evictAll();
//        }
//    }
//
//
//}
