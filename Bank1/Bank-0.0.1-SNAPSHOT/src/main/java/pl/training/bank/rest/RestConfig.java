package pl.training.bank.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("rest.api")
public class RestConfig extends ResourceConfig{

    public RestConfig() {
        packages("pl.training.bank.rest")
            .register(JacksonFeature.class)
            .register(JsonMapperConfig.class);
    }
}
