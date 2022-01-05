package com.example.orderservice.rocketMqConsumer;

import com.alibaba.fastjson.JSON;
import com.example.orderservice.domain.Demo01Message;
import com.example.orderservice.domain.Order;
import com.example.traceIdRocketmq.template.AbstractRocketListener;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@RocketMQMessageListener(topic = Demo01Message.TOPIC, consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC)
public class Demo01Consumer extends AbstractRocketListener<Order> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Demo01Consumer() {
        super(Order.class);
    }

    @Override
    public void doJob(Order body) {
        logger.info("接受到消息通知order={}", JSON.toJSONString(body));
    }

}
