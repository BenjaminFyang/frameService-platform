package com.example.orderservice.consumer;

import com.alibaba.fastjson.JSON;
import com.central.common.constant.CommonConstant;
import com.central.log.trace.MDCTraceUtils;
import com.example.orderservice.domain.Demo01Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;


@Component
@RocketMQMessageListener(topic = Demo01Message.TOPIC, consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC)
public class Demo01Consumer implements RocketMQListener<MessageExt> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(MessageExt message) {

        logger.info("message={}", JSON.toJSONString(message));

        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        logger.info("Consumer-获取消息-主题topic为={}, 消费消息为={}", message.getTopic(), body);

        Map<String, String> properties = message.getProperties();
        String traceId = properties.get(CommonConstant.TRACE_ID_HEADER);
        MDCTraceUtils.putTraceId(traceId);

        logger.info("接受到消息通知order={}", JSON.toJSONString(body));

    }
}
