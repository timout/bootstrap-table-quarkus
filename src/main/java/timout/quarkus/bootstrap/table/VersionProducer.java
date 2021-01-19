package timout.quarkus.bootstrap.table;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import timout.quarkus.bootstrap.table.model.ApplicationVersion;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class VersionProducer {

    @ConfigProperty(name="quarkus.application.name")
    String name;

    @ConfigProperty(name="quarkus.application.version")
    String version;

    @Produces
    public ApplicationVersion version(){
        return new ApplicationVersion(name, version);
    }

}
