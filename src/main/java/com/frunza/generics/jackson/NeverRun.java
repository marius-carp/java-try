package com.frunza.generics.jackson;

import java.time.ZonedDateTime;
import java.util.Optional;

public record NeverRun() implements ResultStatus {


    @Override
    public Status status() {
        return Status.NEVER_RUN;
    }

    @Override
    public Optional<ZonedDateTime> lastUpdatedAt() {
        return Optional.empty();
    }
}
