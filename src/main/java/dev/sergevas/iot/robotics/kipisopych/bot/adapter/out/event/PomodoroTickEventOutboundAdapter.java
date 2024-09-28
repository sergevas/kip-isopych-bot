package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.event.EventSender;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroTickEvent;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroToggleEvent;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroTickEventOutboundAdapter implements EventSender<PomodoroTickEvent> {

    @Inject
    Event<PomodoroTickEvent> pomodoroTickEvent;

    @Override
    public void send(PomodoroTickEvent payload) {
        Log.debugf("Enter send event %s", payload);
        pomodoroTickEvent.fire(payload);
        Log.debug("Exit send event");
    }
}
