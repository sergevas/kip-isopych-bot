package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.EventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroInboundEventAdapter {

    @Inject
    EventHandler<Pomodoro> eventHandler;

    public void onPomodoroEvent(@Observes(during = TransactionPhase.AFTER_SUCCESS) Pomodoro pomodoroEventPayload) {
        Log.debugf("Enter onPomodoroEvent event %s", pomodoroEventPayload);
        eventHandler.handle(pomodoroEventPayload);
        Log.debugf("Exit onPomodoroEvent event %s", pomodoroEventPayload);
    }
}
