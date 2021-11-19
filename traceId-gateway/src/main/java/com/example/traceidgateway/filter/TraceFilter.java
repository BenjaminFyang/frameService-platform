package com.example.traceidgateway.filter;

import cn.hutool.core.util.IdUtil;
import com.central.common.constant.CommonConstant;
import com.central.log.properties.TraceProperties;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 生成日志链路追踪id，并传入header中
 */
@Component
public class TraceFilter implements GlobalFilter, Ordered {

    @Resource
    private TraceProperties traceProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 判断是否开启了链路追踪.
        if (traceProperties.getEnable()) {

            //链路追踪id
            String traceId = IdUtil.fastSimpleUUID();
            MDC.put(CommonConstant.LOG_TRACE_ID, traceId);
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                    .headers(h -> h.add(CommonConstant.TRACE_ID_HEADER, traceId))
                    .build();

            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // Spring IOC容器中Bean的执行顺序的优先级
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
