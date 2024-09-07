package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroStateUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PomodoroStateService implements PomodoroStateUseCase {

    @Inject
    PomodoroReader pomodoroReader;

    @Override
    @Transactional
    public void updateCurrentState() {
        Log.info("Updating Pomodoro current state...");
        var pomodoro = pomodoroReader.read();
        pomodoro.incElapsedTime();
    }
}
