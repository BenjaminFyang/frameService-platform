package com.example.kafka.consumer;

import com.example.kafka.message.DemoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = DemoMessage.TOPIC, groupId = "demo06-consumer-group-" + DemoMessage.TOPIC, concurrency = "2")
    public void onMessage(DemoMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
