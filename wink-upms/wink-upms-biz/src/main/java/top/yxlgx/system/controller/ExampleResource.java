package top.yxlgx.system.controller;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import top.yxlgx.system.config.GreetingConfig;

@Path("/hello")
public class ExampleResource {

    @Inject
    GreetingConfig greetingConfig;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        return Uni.createFrom().item("Hello from RESTEasy Reactive" + " " + greetingConfig.message());
    }
}
