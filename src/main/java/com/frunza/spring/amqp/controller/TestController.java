package com.frunza.spring.amqp.controller;

import com.frunza.spring.amqp.data.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public String create(@RequestBody TestDto requestDto) {
        rabbitTemplate.convertAndSend("", "q.test-creation", requestDto);

        return requestDto.name();
    }

}
