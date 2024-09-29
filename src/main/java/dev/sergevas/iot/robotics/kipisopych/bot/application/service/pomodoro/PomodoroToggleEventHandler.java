package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.EventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.PomodoroBehaviour;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroToggleEvent;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroToggleEventHandler implements EventHandler<PomodoroToggleEvent> {

    @Inject
    PomodoroBehaviour pomodoroBehaviour;

    @Override
    public void handle(PomodoroToggleEvent pomodoroToggleEvent) {
        Log.debugf("Enter handle event %s", pomodoroToggleEvent);
        var pomodoro = pomodoroToggleEvent.pomodoro();
        switch (pomodoro.getType()) {
            case POMODORO -> pomodoroBehaviour.resume(pomodoro);
            case SHORT_BREAK -> pomodoroBehaviour.shortBreak(pomodoro);
            case LONG_BREAK -> pomodoroBehaviour.longBreak(pomodoro);
        }
        Log.debugf("Exit handle event %s", pomodoro);
    }
}
