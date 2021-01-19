package timout.quarkus.bootstrap.table;

import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timout.quarkus.bootstrap.table.model.DeleteEvent;
import timout.quarkus.bootstrap.table.model.Event;
import timout.quarkus.bootstrap.table.model.GenerateEvent;
import timout.quarkus.bootstrap.table.model.RandomDeleteEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Sends generate and deleteRandom events every {read.schedule} seconds
 */

@ApplicationScoped
public class EventManager {

    private final Logger logger = LoggerFactory.getLogger(EventManager.class);

    @Inject
    JobFactory factory;

    @OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 1000)
    @Inject
    @Channel("job-events")
    Emitter<Event> emitter;

    @Scheduled(every="{read.schedule}", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    void scheduleEvent() {
        emitter.send(new RandomDeleteEvent());
        emitter.send(new GenerateEvent(factory.createJob()));
    }

    public void delete(DeleteEvent d) {
        emitter.send(d);
    }

}
