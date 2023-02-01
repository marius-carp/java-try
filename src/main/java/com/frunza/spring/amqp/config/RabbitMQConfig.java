package com.frunza.spring.amqp.config;


import com.frunza.spring.amqp.config.service.ServiceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final CachingConnectionFactory cachingConnectionFactory;

    private final ServiceConfig serviceConfig;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Declarables listenOnEvents() {
        var rabbitMq = serviceConfig.rabbitMq;

        return new Declarables(
                new TopicExchange(rabbitMq.getTopicExchangeEvent(), true, false),
                new Queue(rabbitMq.getInboundQueue().getName()),
                new Binding(rabbitMq.getTopicExchangeEvent(), Binding.DestinationType.QUEUE, rabbitMq.getTopicExchangeEvent(), rabbitMq.getInboundQueue().getBindingKeyTemplate(), Map.of())
        );
    }



}
