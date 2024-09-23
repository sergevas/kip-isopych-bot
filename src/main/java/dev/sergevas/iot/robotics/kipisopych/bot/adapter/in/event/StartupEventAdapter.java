package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.StartupHandler;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StartupEventAdapter {

    @Inject
    StartupHandler startupHandler;

    @Startup
    void startupEvent() {
        Log.debug("Enter startupEvent");
        startupHandler.handleStartup();
        Log.debug("Exit startupEvent");
    }
}
