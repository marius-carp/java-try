package com.frunza.generics.jackson;

import java.time.ZonedDateTime;
import java.util.Optional;

public record Completed<ResultType>(Optional<ResultType> content, Optional<ZonedDateTime> lastUpdatedAt) implements ResultStatus<ResultType> {

    @Override
    public Status status() {
        return Status.COMPLETED;
    }

}
