package timout.quarkus.bootstrap.table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timout.quarkus.bootstrap.table.model.ApplicationVersion;
import timout.quarkus.bootstrap.table.model.DeleteEvent;
import timout.quarkus.bootstrap.table.model.Job;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListResource {

    private final Logger logger = LoggerFactory.getLogger(EventManager.class);

    @Inject
    JobStorage storage;

    @Inject
    EventManager eventManager;

    @Inject
    ApplicationVersion version;

    @Path("/v1/jobs")
    @GET
    public List<Job> jobs() {
        return storage.list();
    }

    @Path("/v1/jobs")
    @DELETE
    public String stop(List<String> ids) {
        for (String id : ids ) {
            logger.info("Deleting job id {}", id);
            storage.get(Long.parseLong(id)).ifPresent( j -> eventManager.delete(new DeleteEvent(j)));
        }
        return "ok";
    }

    @GET
    @Path("/v1/version")
    public ApplicationVersion getVersion() {
        return version;
    }

    @GET
    @Path("/{any : .*}")
    public Response jobListi() {
        return Response.seeOther(URI.create("/")).build();
    }

}

