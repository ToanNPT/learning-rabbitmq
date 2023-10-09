package com.learning.rabbitmq.service;

import com.learning.rabbitmq.common.CommonConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstTestService {


    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FirstTestService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToExchange() {
        String message = " payload is broadcast";
        rabbitTemplate.convertAndSend(CommonConst.FANOUT_EXCHANGE, "", "from fanout exchange " + message);
    }

    public void sendMessageToTopicExchange() {
        String message = "hello everyone VIP member";
        String message2 = "hello error code";
        rabbitTemplate.convertAndSend(
                CommonConst.TOPIC_EXCHANGE,
                "user.important.notify",
                "from important " + message
        );
        rabbitTemplate.convertAndSend(
                CommonConst.TOPIC_EXCHANGE,
                "code.error",
                "from error topic " + message2
        );
    }

    public void sendMessageToDirectExchange(){
        String mess = "I come in from direct exchange";
        rabbitTemplate.convertAndSend(CommonConst.DIRECT_EXCHANGE, "directKey", mess);
    }
}
