package com.frunza.generics.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;
import java.util.Optional;

public class Boot {

    public static void main(String[] args) throws JsonProcessingException {

        var objectMapper = new ObjectMapper();

        var neverRun = new NeverRun();
        var running = new Running(50, Optional.empty());
        var failed = new Failed(Optional.of("Failed to open file on remote"), Optional.empty());
        var completed = new Completed<ResponseData>(Optional.of(ResponseData.empty), Optional.of(ZonedDateTime.now()));
        var completedJson = "{\"content\":{\"empty\":false,\"present\":true},\"lastUpdatedAt\":{\"empty\":false,\"present\":true}}";

        System.out.println(neverRun);
        System.out.println(running);
        System.out.println(failed);
        System.out.println(completed);

        ResultStatus rs = objectMapper.readValue(completedJson, ResultStatus.class);
        System.out.println(rs);
    }
}
