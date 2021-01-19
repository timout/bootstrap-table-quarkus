package timout.quarkus.bootstrap.table;

import timout.quarkus.bootstrap.table.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobStorage {

    List<Job> list();

    Optional<Job> get(long id);
}
