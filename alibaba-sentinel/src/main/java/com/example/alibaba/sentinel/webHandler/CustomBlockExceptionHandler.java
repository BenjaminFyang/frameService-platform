package com.example.alibaba.sentinel.webHandler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.central.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义sentinel异常捕获
 */
@Component
public class CustomBlockExceptionHandler implements BlockExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBlockExceptionHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        LOGGER.error("CustomBlockExceptionHandler捕获流量异常", e);
        throw new BusinessException("捕获流量异常");
    }

}
