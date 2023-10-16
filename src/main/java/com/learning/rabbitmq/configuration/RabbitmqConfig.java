package com.learning.rabbitmq.configuration;

import com.learning.rabbitmq.common.CommonConst;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Declarables fanoutExchange() {
        Queue queue1 = new Queue(CommonConst.FANOUT_QUEUE_1);
        Queue queue2 = new Queue(CommonConst.FANOUT_QUEUE_2);

        FanoutExchange fanoutExchange = new FanoutExchange(CommonConst.FANOUT_EXCHANGE, true, true);
        return new Declarables(
                queue1,
                queue2,
                fanoutExchange,
                BindingBuilder.bind(queue1).to(fanoutExchange),
                BindingBuilder.bind(queue2).to(fanoutExchange)
        );
    }

    @Bean
    public Declarables topicExchange() {
        Queue queue1 = new Queue(CommonConst.TOPIC_QUEUE_1);
        Queue queue2 = new Queue(CommonConst.TOPIC_QUEUE_2);
        TopicExchange topicExchange = new TopicExchange(CommonConst.TOPIC_EXCHANGE, true, false);
        return new Declarables(
                queue1,
                queue2,
                topicExchange,
                BindingBuilder.bind(queue1).to(topicExchange).with("*.important.*"),
                BindingBuilder.bind(queue2).to(topicExchange).with("#.error")
        );
    }

    @Bean
    public Declarables directExchange() {
        Queue queue1 = new Queue(CommonConst.DIRECT_QUEUE);
        DirectExchange directExchange = new DirectExchange(CommonConst.DIRECT_EXCHANGE);
        return new Declarables(
                queue1,
                directExchange,
                BindingBuilder.bind(queue1).to(directExchange).with("directKey")
        );
    }

    // example 2, rpc
    public static final String REQUEST_QUEUE = "queue_request";
    public static final String TOPIC_EXCHANGE = "request_exchange";

    public static final String RELY_QUEUE = "queue_rely";

    @Bean
    public Declarables declareRabbit() {
        Queue queue = new Queue(REQUEST_QUEUE);
        Queue relyQueue = new Queue(RELY_QUEUE);
        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE);
        return new Declarables(
                queue,
                relyQueue,
                topicExchange,
                BindingBuilder.bind(queue).to(topicExchange).with(REQUEST_QUEUE),
                BindingBuilder.bind(relyQueue).to(topicExchange).with(RELY_QUEUE)
        );
    }


}
