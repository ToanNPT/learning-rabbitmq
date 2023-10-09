package com.learning.rabbitmq.controller;

import com.learning.rabbitmq.common.ApiConstants;
import com.learning.rabbitmq.service.FirstTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstTestController {

    private final FirstTestService service;

    @Autowired
    public FirstTestController(FirstTestService firstTestService) {
        this.service = firstTestService;
    }

    @GetMapping(ApiConstants.TEST_SEND_FANOUT_EXCHANGE)
    public void sendMessage() {
        service.sendMessageToExchange();
    }

    @GetMapping(ApiConstants.TEST_SEND_TOPIC_EXCHANGE)
    public void sendTopicExchange() {
        service.sendMessageToTopicExchange();
    }

    @GetMapping(ApiConstants.TEST_SEND_DIRECT_EXCHANGE)
    public void sendDirectExchange(){
        service.sendMessageToDirectExchange();
    }
}
