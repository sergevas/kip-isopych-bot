package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.EventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro.PomodoroTickEventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro.PomodoroToggleEventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroTickEvent;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroToggleEvent;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroInboundEventAdapter {

    @Inject
    PomodoroToggleEventHandler pomodoroToggleEventEventHandler;
    @Inject
    PomodoroTickEventHandler pomodoroTickEventEventHandler;

    public void onPomodoroToggleEvent(@Observes(during = TransactionPhase.AFTER_SUCCESS) PomodoroToggleEvent pomodoroToggleEventPayload) {
        Log.debugf("Enter onPomodoroToggleEvent event %s", pomodoroToggleEventPayload);
        pomodoroToggleEventEventHandler.handle(pomodoroToggleEventPayload);
        Log.debug("Exit onPomodoroToggleEvent");
    }

    public void onPomodoroTickEvent(@Observes(during = TransactionPhase.AFTER_SUCCESS) PomodoroTickEvent pomodoroTickEvent) {
        Log.debugf("Enter onPomodoroTickEvent event %s", pomodoroTickEvent);
        pomodoroTickEventEventHandler.handle(pomodoroTickEvent);
        Log.debug("Exit onPomodoroTickEvent");
    }
}
