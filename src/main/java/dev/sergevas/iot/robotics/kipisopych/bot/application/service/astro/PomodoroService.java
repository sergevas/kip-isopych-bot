package dev.sergevas.iot.robotics.kipisopych.bot.application.service.astro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroSetupUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroConfig;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PomodoroService implements PomodoroSetupUseCase {

    @Override
    public void setup(PomodoroConfig pomodoroConfig) {
        try {
            Log.infof("Setting up the Pomodoro Timer with %s", pomodoroConfig);
        } catch (Exception e) {
            Log.error(e);
        }
    }
}
