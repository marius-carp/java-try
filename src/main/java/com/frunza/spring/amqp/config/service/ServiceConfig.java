package com.frunza.spring.amqp.config.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service")
@Configuration
@Getter
public class ServiceConfig {

    public final RabbitMq rabbitMq = new RabbitMq();

    @Getter
    @Setter
    @Configuration
    @ConfigurationProperties(prefix = "service.rabbitmq")
    @EnableConfigurationProperties
    public static class RabbitMq {
        private String topicExchangeEvent;
        private InboundQueue inboundQueue = new InboundQueue();

        @Getter
        @Setter
        @Configuration
        public static class InboundQueue {
            private String name;
            private String bindingKeyTemplate;
        }
    }

}
