package com.learning.rabbitmq.configuration;

import com.learning.rabbitmq.common.CommonConst;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerDirectExchange {

    @RabbitListener(queues = {CommonConst.DIRECT_QUEUE})
    public void printMessageFromDirectExchange(String message){
        System.out.println(message);
    }
}
