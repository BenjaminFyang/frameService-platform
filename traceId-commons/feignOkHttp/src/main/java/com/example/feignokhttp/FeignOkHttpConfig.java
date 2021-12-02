package com.example.feignokhttp;

import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
public class FeignOkHttpConfig {

    private okhttp3.OkHttpClient okHttpClient;

    @Bean
    @ConditionalOnMissingBean(ConnectionPool.class)
    public ConnectionPool httpClientConnectionPool(FeignHttpClientProperties httpClientProperties, OkHttpClientConnectionPoolFactory connectionPoolFactory) {
        int maxTotalConnections = httpClientProperties.getMaxConnections();
        long timeToLive = httpClientProperties.getTimeToLive();
        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
    }

    @Bean
    @ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
    public okhttp3.OkHttpClient okHttpClient(OkHttpClientFactory httpClientFactory, ConnectionPool connectionPool, FeignClientProperties feignClientProperties, FeignHttpClientProperties feignHttpClientProperties) {
        FeignClientProperties.FeignClientConfiguration defaultConfig = feignClientProperties.getConfig().get("default");
        int connectionTimeout = feignHttpClientProperties.getConnectionTimeout();
        int readTimeout = defaultConfig.getReadTimeout();
        boolean disableSslValidation = feignHttpClientProperties.isDisableSslValidation();
        boolean followRedirects = feignHttpClientProperties.isFollowRedirects();
        this.okHttpClient = httpClientFactory.createBuilder(disableSslValidation)
                // 设置读超时
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                //设置连接超时
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .followRedirects(followRedirects)
                .connectionPool(connectionPool)
                // 这里设置我们自定义的拦截器
                .addInterceptor(new MyOkhttpInterceptor())
                //设置写超时
                .writeTimeout(10, TimeUnit.SECONDS)
                //是否自动重连
                .retryOnConnectionFailure(true)
                .build();
        return this.okHttpClient;
    }

    @PreDestroy
    public void destroy() {
        if (this.okHttpClient != null) {
            this.okHttpClient.dispatcher().executorService().shutdown();
            this.okHttpClient.connectionPool().evictAll();
        }
    }

}
