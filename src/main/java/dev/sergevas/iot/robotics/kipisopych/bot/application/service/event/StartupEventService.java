package dev.sergevas.iot.robotics.kipisopych.bot.application.service.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.StartupHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.StartBehavior;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class StartupEventService implements StartupHandler {

    @Inject
    StartBehavior startBehavior;

    @Override
    public void handleStartup() {
        Log.debug("Enter handleStartup()");
        startBehavior.start();
        Log.debug("Exit handleStartup()");
    }
}
