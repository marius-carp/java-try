package com.frunza.generics.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.ZonedDateTime;
import java.util.Optional;

public record Completed<ResultType>(Optional<ResultType> content, Optional<ZonedDateTime> lastUpdatedAt) implements ResultStatus<ResultType> {

    @Override
    @JsonDeserialize
    public Status status() {
        return Status.COMPLETED;
    }

}
