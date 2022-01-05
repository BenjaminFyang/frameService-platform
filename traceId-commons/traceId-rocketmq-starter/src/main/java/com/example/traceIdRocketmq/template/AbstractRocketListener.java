package com.example.traceIdRocketmq.template;

import com.alibaba.fastjson.JSON;
import com.central.common.constant.CommonConstant;
import com.central.log.trace.MDCTraceUtils;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public abstract class AbstractRocketListener<T> implements RocketMQListener<MessageExt> {

    private final Class<T> tClass;

    public AbstractRocketListener(Class<T> tClass) {
        this.tClass = tClass;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(MessageExt messageExt) {

        String traceId = messageExt.getProperty(CommonConstant.TRACE_ID_HEADER);
        MDCTraceUtils.putTraceId(traceId);

        String body = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        logger.info("Consumer-获取消息-主题topic为={}, 消费消息为={}", messageExt.getTopic(), body);

        doJob(JSON.parseObject(body, tClass));

    }

    /**
     * 模版方法 业务消息处理
     *
     * @param t the Object
     */
    public abstract void doJob(T t);

}
