package com.frunza.generics.jackson;

import java.time.ZonedDateTime;
import java.util.Optional;

public record Running(int completionPercentage, Optional<ZonedDateTime> lastUpdatedAt) implements ResultStatus {
    @Override
    public Status status() {
        return Status.RUNNING;
    }
}
