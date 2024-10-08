package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroStateUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.event.EventSender;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroException;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroTickEvent;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.event.PomodoroToggleEvent;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import static dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroException.FETCH_ERROR_MSG;

@ApplicationScoped
public class PomodoroStateService implements PomodoroStateUseCase {

    @Inject
    PomodoroReader pomodoroReader;
    @Inject
    EventSender<PomodoroToggleEvent> pomodoroToggleEventEventSender;
    @Inject
    EventSender<PomodoroTickEvent> pomodoroTickEventEventSender;

    @Override
    @Transactional
    public void updateCurrentState() {
        Log.debug("---------- Updating Pomodoro current state ----------");
        var pomodoro = pomodoroReader.read().orElseThrow(() -> new PomodoroException(FETCH_ERROR_MSG));
        Log.debug(pomodoro);
        Log.debug("--------------------------------------------------");
        var elapsedTime = pomodoro.incElapsedTime();
        Log.debugf("Elapsed time: %s", elapsedTime);
        pomodoroTickEventEventSender.send(new PomodoroTickEvent(pomodoro));
        var isFinished = pomodoro.isFinished();
        Log.debugf("Is finished: %s", isFinished);
        if (isFinished) {
            Log.debug("Toggle!");
            pomodoro.toggle();
            pomodoroToggleEventEventSender.send(new PomodoroToggleEvent(pomodoro));
        }
        Log.debug("---------- The Pomodoro new state ----------");
        Log.debug(pomodoro);
        Log.debug("--------------------------------------------------");
    }
}
