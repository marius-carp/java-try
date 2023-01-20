package com.frunza.generics.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.ZonedDateTime;
import java.util.Optional;

public record Failed(Optional<String> reason, Optional<ZonedDateTime> lastUpdatedAt) implements ResultStatus {
    @Override
    @JsonDeserialize
    public Status status() {
        return Status.FAILED;
    }
}
