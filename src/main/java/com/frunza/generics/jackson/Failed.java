package com.frunza.generics.jackson;

import java.time.ZonedDateTime;
import java.util.Optional;

public record Failed(Optional<String> reason, Optional<ZonedDateTime> lastUpdatedAt) implements ResultStatus {
    @Override
    public Status status() {
        return Status.FAILED;
    }
}
