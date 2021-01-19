package timout.quarkus.bootstrap.table.model;

import java.util.Objects;

public record Job(
        long id,
        String name,
        String notes,
        long rank
) {
    public Job {
        Objects.requireNonNull(name);
        Objects.requireNonNull(notes);
    }

    // Temporary: Quarkus jsonb lib does not support records yet.
    // There are some workarounds but for the sake of simplicity getters were chosen. :)

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public long getRank() {
        return rank;
    }
}
