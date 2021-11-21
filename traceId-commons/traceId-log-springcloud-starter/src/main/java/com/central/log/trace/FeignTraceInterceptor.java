package com.central.log.trace;

import com.central.log.properties.TraceProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @see https://cloud.tencent.com/developer/article/1600718
 * Feign的拦截器RequestInterceptor
 * SpringCloud的微服务使用Feign进行服务间调用的时候可以使用RequestInterceptor统一拦截请求来完成设置header等相关请求，
 */
public class FeignTraceInterceptor implements RequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignTraceInterceptor.class);


    @Resource
    private TraceProperties traceProperties;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        LOGGER.info("FeignTraceInterceptor.request: {}", requestTemplate.url());

        if (traceProperties.getEnable()) {
            //传递日志traceId
            String traceId = MDCTraceUtils.getTraceId();
            if (!StringUtils.isEmpty(traceId)) {
                requestTemplate.header(MDCTraceUtils.TRACE_ID_HEADER, traceId);
            }
        }
    }


}
