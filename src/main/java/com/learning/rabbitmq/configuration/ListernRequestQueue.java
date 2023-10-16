package com.learning.rabbitmq.configuration;

import org.apache.logging.log4j.LogManager;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
@Service
public class ListernRequestQueue {

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger logger = LogManager.getLogger(this.getClass());

    @RabbitListener(queues = {RabbitmqConfig.REQUEST_QUEUE})
    public void handleListenRequestQueue(Message message) {
        byte[] body = message.getBody();
        System.out.println("RECEIVE REQUEST: " + new String(message.getBody()));
        Message build = MessageBuilder.withBody(("I am the server, I received the message from the client：" + new String(body)).getBytes()).build();
        build.getMessageProperties().setHeader("spring_returned_message_correlation", message.getMessageProperties().getCorrelationId());
        CorrelationData correlation = new CorrelationData(message.getMessageProperties().getCorrelationId());
        rabbitTemplate.sendAndReceive(RabbitmqConfig.TOPIC_EXCHANGE, RabbitmqConfig.RELY_QUEUE, build, correlation);
    }
}
