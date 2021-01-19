package timout.quarkus.bootstrap.table.model;

import java.util.Objects;

final public record DeleteEvent(Job job) implements Event {
    public DeleteEvent {
        Objects.requireNonNull(job);
    }
}
