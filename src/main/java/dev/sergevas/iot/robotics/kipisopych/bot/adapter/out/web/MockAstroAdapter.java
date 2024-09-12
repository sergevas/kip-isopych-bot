package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.web;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.astro.AstroFetcher;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.astro.Astronauts;
import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
@DefaultBean
public class MockAstroAdapter implements AstroFetcher {
    @Override
    public Astronauts fetch() {
        return new Astronauts(List.of(), 14, "success");
    }
}
