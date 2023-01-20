package com.frunza.generics.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.ZonedDateTime;
import java.util.Optional;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "status",
        defaultImpl = NeverRun.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NeverRun.class, name = "neverRun"),
        @JsonSubTypes.Type(value = Running.class, name = "running"),
        @JsonSubTypes.Type(value = Failed.class, name = "failed"),
        @JsonSubTypes.Type(value = Completed.class, name = "completed")
})
sealed public interface ResultStatus<ResultType> permits Completed, Failed, NeverRun, Running {

    enum Status {
        @JsonProperty("neverRun")
        NEVER_RUN("neverRun"),
        @JsonProperty("running")
        RUNNING("running"),
        @JsonProperty("failed")
        FAILED("failed"),
        @JsonProperty("completed")
        COMPLETED("completed");

        public final String asString;
        Status(String asString) {
            this.asString = asString;
        }
    }

    Status status();

    Optional<ZonedDateTime> lastUpdatedAt();

    default <OtherResultType extends ResultType> ResultStatus<ResultType> compareAndSetLastUpdated(ResultStatus<OtherResultType> oldStatus) {
       if (copyAsLastUpdated(Optional.empty()) == oldStatus.copyAsLastUpdated(Optional.empty()))
           return this;
       else {
           return copyAsLastUpdatedNow();
       }
    }

    default ResultStatus<ResultType> copyAsLastUpdatedNow() {
        return copyAsLastUpdated(Optional.of(ZonedDateTime.now()));
    }

    default ResultStatus<ResultType> copyAsLastUpdated(Optional<ZonedDateTime> zonedDateTime) {
       return switch (this) {
           case NeverRun nr -> nr;
           case Running r -> new Running(r.completionPercentage(), zonedDateTime);
           case Failed f -> new Failed(f.reason(), zonedDateTime);
           case Completed<ResultType> c -> new Completed<>(c.content(), zonedDateTime);
       };
    }
}
