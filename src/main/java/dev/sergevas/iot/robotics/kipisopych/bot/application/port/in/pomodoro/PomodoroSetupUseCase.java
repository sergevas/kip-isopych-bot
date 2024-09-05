package dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroConfig;

public interface PomodoroSetupUseCase {
    void setup(PomodoroConfig pomodoroConfig);
}
