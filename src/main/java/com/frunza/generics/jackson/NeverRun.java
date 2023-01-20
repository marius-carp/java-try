package com.frunza.generics.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.ZonedDateTime;
import java.util.Optional;

public record NeverRun() implements ResultStatus {


    @Override
    @JsonDeserialize
    public Status status() {
        return Status.NEVER_RUN;
    }

    @Override
    @JsonSerialize
    public Optional<ZonedDateTime> lastUpdatedAt() {
        return Optional.empty();
    }
}
