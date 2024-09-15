package dev.sergevas.iot.robotics.kipisopych.bot.application.service;

import dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.StartBehavior;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BootHook {

    @Inject
    StartBehavior behavior;

    public void bootUp() {
        behavior.ping();
    }
}
