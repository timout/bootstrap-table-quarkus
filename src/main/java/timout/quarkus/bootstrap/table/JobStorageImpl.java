package timout.quarkus.bootstrap.table;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timout.quarkus.bootstrap.table.model.*;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;;

@ApplicationScoped
public class JobStorageImpl implements JobStorage {

    private final Logger logger = LoggerFactory.getLogger(JobStorageImpl.class);

    @ConfigProperty(name="storageMaxSize", defaultValue = "25")
    int storageMaxSize;

    private final ConcurrentHashMap<Long, Job> m = new ConcurrentHashMap<>();

    public List<Job> list() {
        return new ArrayList<>(m.values());
    }

    public Optional<Job> get(long id) {
        var v = m.get(id);
        return Optional.ofNullable(v);
    }

    @Incoming("job-events")
    void processEvent(Event event) {
        if ( event instanceof GenerateEvent e) {
            add(e);
        } else if (event instanceof DeleteEvent e) {
            delete(e);
        } else if (event instanceof RandomDeleteEvent) {
            deleteRandom();
        }
    }

    private void delete(DeleteEvent e) {
        logger.info("Deleting {}", e.job());
        m.remove(e.job().id());
    }

    private void add(GenerateEvent e) {
        logger.info("Adding {}", e.job());
        m.put(e.job().id(), e.job());
    }

    private void deleteRandom() {
        if ( m.size() >= storageMaxSize ) {
            var keySet = new ArrayList<>(m.keySet());
            int i = ThreadLocalRandom.current().nextInt(keySet.size());
            logger.info("Removing element {}", keySet.get(i));
            m.remove(keySet.get(i));
        }
    }

}
