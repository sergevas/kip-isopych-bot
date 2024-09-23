package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.EventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.PomodoroBehaviour;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroEventHandler implements EventHandler<Pomodoro> {

    @Inject
    PomodoroBehaviour pomodoroBehaviour;

    @Override
    public void handle(Pomodoro pomodoro) {
        Log.debugf("Enter handle event %s", pomodoro);
        switch (pomodoro.getType()) {
            case POMODORO -> pomodoroBehaviour.resume();
            case SHORT_BREAK -> pomodoroBehaviour.shortBreak();
            case LONG_BREAK -> pomodoroBehaviour.longBreak();
        }
        Log.debugf("Exit handle event %s", pomodoro);
    }
}
