package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.web;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.Astronauts;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RegisterRestClient(configKey = "astros")
public interface AstroRestClient {

    @GET
    @Produces(APPLICATION_JSON)
    @Retry(maxDuration = 31000, jitter = 500, maxRetries = 2)
    @Timeout(10000)
    Astronauts getAstronauts();
}
