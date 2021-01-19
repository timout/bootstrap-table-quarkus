package timout.quarkus.bootstrap.table;

import timout.quarkus.bootstrap.table.model.Job;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class JobFactory {

    private final AtomicLong counter = new AtomicLong(1);

    public Job createJob() {
        long id = counter.getAndIncrement();
        String name = String.format("Item_%d", id);
        return new Job(
                id,
                name,
                Util.generatingRandomAlphabeticString(50),
                Util.generateRank());
    }
}
