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

}
