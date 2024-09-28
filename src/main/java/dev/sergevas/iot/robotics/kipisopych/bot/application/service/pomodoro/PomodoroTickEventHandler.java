package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event.EventHandler;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.PomodoroBehaviour;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroTickEvent;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroToggleEvent;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroTickEventHandler implements EventHandler<PomodoroTickEvent> {

    @Inject
    PomodoroBehaviour pomodoroBehaviour;

    @Override
    public void handle(PomodoroTickEvent pomodoroTickEvent) {
        Log.debugf("Enter handle event %s", pomodoroTickEvent);
        var pomodoro = pomodoroTickEvent.pomodoro();
        // TODO: implement method
        Log.debugf("Exit handle event %s", pomodoro);
    }
}
