package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.event.EventSender;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroOutboundEventAdapter implements EventSender<Pomodoro> {

    @Inject
    Event<Pomodoro> pomodoroStateEvent;

    @Override
    public void send(Pomodoro pomodoro) {
        Log.debugf("Enter send event %s", pomodoro);
        pomodoroStateEvent.fire(pomodoro);
        Log.debugf("Exit send event %s", pomodoro);
    }
}
