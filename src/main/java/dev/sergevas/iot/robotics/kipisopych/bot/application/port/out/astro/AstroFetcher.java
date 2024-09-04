package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.astro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.Astronauts;

public interface AstroFetcher {

    Astronauts fetch();
}
