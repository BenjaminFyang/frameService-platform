package com.example.feignokhttp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 对feign微服务调用的拦截 自定义返回结果.
 */
@Component
public class MyOkhttpInterceptor implements Interceptor {

    Logger logger = LoggerFactory.getLogger(MyOkhttpInterceptor.class);

    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request request = originRequest.newBuilder().build();
        if (StringUtils.isNotEmpty(originRequest.header("Accept-Encoding"))) {
            request = originRequest.newBuilder().removeHeader("Accept-Encoding").build();
        }

        long doTime = System.nanoTime();
        Response response = null;
        try {
            response = chain.proceed(request);
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            long currentTime = System.nanoTime();
            logger.info(String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n", response.request().url(), responseBody.string(), (currentTime - doTime) / 1e6d));
        } catch (IOException e) {
            logger.error("MyOkhttpInterceptor调用微服务异常需处理", e);
        }
        return response;
    }


}
