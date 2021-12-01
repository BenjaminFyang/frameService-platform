package com.example.orderservice.config;

import io.micrometer.core.instrument.util.StringUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class MyOkhttpInterceptor implements Interceptor {

    Logger logger = LoggerFactory.getLogger(MyOkhttpInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request request = originRequest.newBuilder().build();
        if (StringUtils.isNotEmpty(originRequest.header("Accept-Encoding"))) {
            request = originRequest.newBuilder().removeHeader("Accept-Encoding").build();
        }

        long doTime = System.nanoTime();
        Response response = chain.proceed(request);
        long currentTime = System.nanoTime();
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        logger.info(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n", response.request().url(), responseBody.string(), (currentTime - doTime) / 1e6d));
        return response;
    }

}
