package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.web;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.astro.AstroFetcher;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.astro.Astronauts;
import io.quarkus.arc.profile.UnlessBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@UnlessBuildProfile("test")
public class AstroHttpAdapter implements AstroFetcher {

    @RestClient
    AstroRestClient astroRestClient;

    @Override
    public Astronauts fetch() {
        return astroRestClient.getAstronauts();
    }
}
