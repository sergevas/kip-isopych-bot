package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroControlUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroException;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroWriter;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroConfig;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import static dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroException.FETCH_ERROR_MSG;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState.PAUSED;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState.STARTED;

@ApplicationScoped
public class PomodoroControlService implements PomodoroControlUseCase {

    @Inject
    PomodoroWriter pomodoroWriter;
    @Inject
    PomodoroReader pomodoroReader;

    @Override
    @Transactional
    public void setup(PomodoroConfig pomCfg) {
        try {
            Log.debugf("Setting up the Pomodoro Timer with initial config %s", pomCfg);
            var pomodoroOpt = pomodoroReader.read();
            var pomodoro = pomodoroOpt.orElse(new Pomodoro()).reset();
            pomodoro.setPomodoroDuration(pomCfg.pomodoroDuration());
            pomodoro.setShortBreakDuration(pomCfg.shortBreakDuration());
            pomodoro.setLongBreakDuration(pomCfg.longBreakDuration());
            pomodoro.setNumOfPomodoros(pomCfg.numOfPomodoros());
            if (pomodoroOpt.isEmpty()) {
                pomodoroWriter.write(pomodoro);
                Log.debugf("Persisting a new Pomodoro setup %s", pomCfg);
            }
        } catch (Exception e) {
            Log.error(e);
        }
    }

    @Override
    @Transactional
    public void resume() {
        try {
            Log.debug("Resume Pomodoro Timer");
            pomodoroReader.read().orElseThrow(() -> new PomodoroException(FETCH_ERROR_MSG)).setState(STARTED);
        } catch (Exception e) {
            Log.error(e);
        }
    }

    @Override
    @Transactional
    public void pause() {
        try {
            Log.info("Pause Pomodoro Timer");
            pomodoroReader.read().orElseThrow(() -> new PomodoroException(FETCH_ERROR_MSG)).setState(PAUSED);
        } catch (Exception e) {
            Log.error(e);
        }
    }
}
