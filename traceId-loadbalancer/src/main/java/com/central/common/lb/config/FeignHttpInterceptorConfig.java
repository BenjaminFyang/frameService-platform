package com.central.common.lb.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * feign拦截器，只包含http相关数据
 *
 * @author zlt
 */
public class FeignHttpInterceptorConfig {
    protected List<String> requestHeaders = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        requestHeaders.add(SecurityConstants.USER_ID_HEADER);
        requestHeaders.add(SecurityConstants.USER_HEADER);
        requestHeaders.add(SecurityConstants.ROLE_HEADER);
        requestHeaders.add(CommonConstant.Z_L_T_VERSION);
    }

    /**
     * 使用feign client访问别的微服务时，将上游传过来的access_token、username、roles等信息放入header传递给下一个服务
     */
    @Bean
    public RequestInterceptor httpFeignInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    String headerName;
                    String headerValue;
                    while (headerNames.hasMoreElements()) {
                        headerName = headerNames.nextElement();
                        if (requestHeaders.contains(headerName)) {
                            headerValue = request.getHeader(headerName);
                            template.header(headerName, headerValue);
                        }
                    }
                }
            }
        };
    }


}
