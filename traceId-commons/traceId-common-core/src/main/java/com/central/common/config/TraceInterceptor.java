package com.central.common.config;

import com.central.common.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = request.getHeader(CommonConstant.TRACE_ID_HEADER);
        if (StringUtils.isNotEmpty(traceId)) {
            MDC.put(CommonConstant.TRACE_ID_HEADER, traceId);
        }
        return true;
    }
}
