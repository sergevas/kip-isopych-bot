package dev.sergevas.iot.robotics.kipisopych.bot.application.service.astro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.astro.AstroUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.astro.AstroFetchException;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.astro.AstroFetcher;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.AstroBehaviour;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class AstroService implements AstroUseCase {

    @Inject
    AstroFetcher astroFetcher;
    @Inject
    AstroBehaviour astroBehaviour;

    @Override
    public void checkAstronautsOnOrbitAndShoutAboutIt() {
        try {
            var astronauts = Optional.ofNullable(astroFetcher.fetch()).orElseThrow(AstroFetchException::new);
            Log.debugf("Have got astronauts: %s", astronauts);
            astroBehaviour.success(astronauts.number());
        } catch (Exception e) {
            Log.error(e);
            astroBehaviour.failedToCheck();
        }
    }
}
