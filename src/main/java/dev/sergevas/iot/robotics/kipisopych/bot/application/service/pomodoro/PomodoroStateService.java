package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroStateUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class PomodoroStateService implements PomodoroStateUseCase {

    private static final Logger log = LoggerFactory.getLogger(PomodoroStateService.class);
    @Inject
    PomodoroReader pomodoroReader;

    @Override
    @Transactional
    public void updateCurrentState() {
        Log.debug("---------- Updating Pomodoro current state ----------");
        var pomodoro = pomodoroReader.read();
        Log.debug(pomodoro);
        Log.debug("--------------------------------------------------");
        var elapsedTime = pomodoro.incElapsedTime();
        Log.debugf("Elapsed time: %s", elapsedTime);
        var isFinished = pomodoro.isFinished();
        Log.debugf("Is finished: %s", isFinished);
        if (pomodoro.isFinished()) {
            Log.debug("Toggle!");
            pomodoro.toggle();
        } else {
            Log.debug("Increment current number!");
            pomodoro.incCurrentNumber();
        }
        Log.debug("---------- The Pomodoro new state ----------");
        Log.debug(pomodoro);
        Log.debug("--------------------------------------------------");
    }
}
