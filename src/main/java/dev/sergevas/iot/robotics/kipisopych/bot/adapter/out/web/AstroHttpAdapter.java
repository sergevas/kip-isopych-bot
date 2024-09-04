package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.web;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.astro.AstroFetcher;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.Astronauts;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AstroHttpAdapter implements AstroFetcher {

    @RestClient
    AstroRestClient astroRestClient;

    @Override
    public Astronauts fetch() {
        return astroRestClient.getAstronauts();
    }
}
