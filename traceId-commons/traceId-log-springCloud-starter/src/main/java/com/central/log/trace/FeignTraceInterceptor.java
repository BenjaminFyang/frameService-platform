package com.central.log.trace;

import com.central.log.properties.TraceProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


//@ConditionalOnClass(value = {RequestInterceptor.class})
public class FeignTraceInterceptor implements RequestInterceptor {

    @Resource
    private TraceProperties traceProperties;
//
//    @Bean
//    public RequestInterceptor feignTraceInterceptor() {
//        return template -> {
//            if (traceProperties.getEnable()) {
//                //传递日志traceId
//                String traceId = MDCTraceUtils.getTraceId();
//                if (!StringUtils.isEmpty(traceId)) {
//                    template.header(MDCTraceUtils.TRACE_ID_HEADER, traceId);
//                }
//            }
//        };
//    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (traceProperties.getEnable()) {
            //传递日志traceId
            String traceId = MDCTraceUtils.getTraceId();
            if (!StringUtils.isEmpty(traceId)) {
                requestTemplate.header(MDCTraceUtils.TRACE_ID_HEADER, traceId);
            }
        }
    }


}
