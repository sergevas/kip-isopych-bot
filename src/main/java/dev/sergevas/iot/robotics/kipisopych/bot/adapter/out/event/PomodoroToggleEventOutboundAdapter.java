package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.event.EventSender;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroToggleEvent;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroToggleEventOutboundAdapter implements EventSender<PomodoroToggleEvent> {

    @Inject
    Event<PomodoroToggleEvent> pomodoroToggleEvent;

    @Override
    public void send(PomodoroToggleEvent payload) {
        Log.debugf("Enter send event %s", payload);
        this.pomodoroToggleEvent.fire(payload);
        Log.debug("Exit send event");
    }
}
