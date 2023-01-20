package com.frunza.generics.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.ZonedDateTime;
import java.util.Optional;

public class Boot {

    public static void main(String[] args) throws JsonProcessingException {

        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());

        var neverRun = new NeverRun();
        var running = new Running(50, Optional.empty());
        var failed = new Failed(Optional.of("Failed to open file on remote"), Optional.empty());
        var completed = new Completed<ResponseData>(Optional.of(ResponseData.empty), Optional.of(ZonedDateTime.now()));
        var neverRunJson = "{\"status\":\"neverRun\"}";
        var runningJson = "{\"status\":\"running\",\"completionPercentage\":50}";
        var failedJson = "{\"status\":\"failed\",\"reason\":\"Failed to open file on remote\"}";
        var completedJson = "{\"status\":\"completed\",\"content\":{\"id\":\"id\",\"fileRef\":\"http://frunza.com/file.dat\",\"durationInSeconds\":1000},\"lastUpdatedAt\":1674214656.226896000}";

        System.out.println(neverRun);
        System.out.println(running);
        System.out.println(failed);
        System.out.println(completed);

        System.out.println(objectMapper.writeValueAsString(neverRun));
        System.out.println(objectMapper.writeValueAsString(running));
        System.out.println(objectMapper.writeValueAsString(failed));
        System.out.println(objectMapper.writeValueAsString(completed));

        ResultStatus rs = objectMapper.readValue(neverRunJson, ResultStatus.class);
        ResultStatus rs1 = objectMapper.readValue(runningJson, ResultStatus.class);
        ResultStatus rs2 = objectMapper.readValue(failedJson, ResultStatus.class);
        ResultStatus rs3 = objectMapper.readValue(completedJson, ResultStatus.class);

        System.out.println(rs);
        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);
    }
}
