package com.frunza.spring.amqp.data;

import java.io.Serializable;

public record TestDto(String name, int age) implements Serializable {

}