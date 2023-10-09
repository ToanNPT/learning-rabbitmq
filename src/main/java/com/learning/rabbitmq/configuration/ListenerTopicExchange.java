package com.learning.rabbitmq.configuration;

import com.learning.rabbitmq.common.CommonConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerTopicExchange {
    @RabbitListener(queues = {CommonConst.TOPIC_QUEUE_1})
    public void receiveMessageFromTopic1(String message) {
        System.out.println("Received topic 1 (" + "*.important.*" + ") message: " + message);
    }

    @RabbitListener(queues = {CommonConst.TOPIC_QUEUE_2})
    public void receiveMessageFromTopic2(String message) {
        System.out.println("Received topic 2 (" + "#.error" + ") message: " + message);
    }
}
