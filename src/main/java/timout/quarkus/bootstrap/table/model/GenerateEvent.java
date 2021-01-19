package timout.quarkus.bootstrap.table.model;

import java.util.Objects;

final public record GenerateEvent(Job job) implements Event {
    public GenerateEvent {
        Objects.requireNonNull(job);
    }
}
