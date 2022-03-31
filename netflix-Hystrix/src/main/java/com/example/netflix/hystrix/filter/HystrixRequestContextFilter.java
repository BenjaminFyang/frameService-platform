package com.example.netflix.hystrix.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/")
public class HystrixRequestContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 初始化 HystrixRequestContext
        // 继续过滤器
        try (HystrixRequestContext ignored = HystrixRequestContext.initializeContext()) {
            chain.doFilter(request, response);
        }
        // 销毁 HystrixRequestContext
    }

}
