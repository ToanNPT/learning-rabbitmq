package com.learning.rabbitmq.configuration;

import com.learning.rabbitmq.common.CommonConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerFanoutExchange {

    @RabbitListener(queues = {CommonConst.FANOUT_QUEUE_1})
    public void receiveMessageFromFanout1(String message) {
        System.out.println("Received fanout 1 message: " + message);
    }

    @RabbitListener(queues = {CommonConst.FANOUT_QUEUE_2})
    public void receiveMessageFromFanout2(String message) {
        System.out.println("Received fanout 2 message: " + message);
    }

}
