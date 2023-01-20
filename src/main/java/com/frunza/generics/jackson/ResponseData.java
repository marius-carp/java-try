package com.frunza.generics.jackson;

public record ResponseData(String id, String fileRef, Long durationInSeconds) {

    static ResponseData empty = new ResponseData("id", "http://frunza.com/file.dat", 1000L);
}
