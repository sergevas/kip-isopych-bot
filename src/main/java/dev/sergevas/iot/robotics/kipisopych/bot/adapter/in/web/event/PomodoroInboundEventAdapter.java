package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.web.event;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.EventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.PomodoroBehaviour;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroInboundEventAdapter implements EventHandler<Pomodoro> {

    @Inject
    PomodoroBehaviour pomodoroBehaviour;

    public void onPomodoroEvent(@Observes Pomodoro pomodoroEventPayload) {
        Log.debugf("Enter onPomodoroEvent event %s", pomodoroEventPayload);
        handle(pomodoroEventPayload);
        Log.debugf("Exit onPomodoroEvent event %s", pomodoroEventPayload);
    }

    @Override
    public void handle(Pomodoro pomodoro) {
        Log.debugf("Enter handle event %s", pomodoro);
        switch (pomodoro) {
            case pomodoro. -> pomodoroBehaviour.pause();
            ca
        }
        Log.debugf("Exit handle event %s", pomodoro);
    }
}
