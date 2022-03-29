package com.example.kafka.consumer;

import com.example.kafka.message.Demo06Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Demo06Consumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = Demo06Message.TOPIC, groupId = "demo06-consumer-group-" + Demo06Message.TOPIC, concurrency = "2")
    public void onMessage(Demo06Message message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
