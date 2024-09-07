package dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroConfig;

public interface PomodoroControlUseCase {

    void setup(PomodoroConfig pomodoroConfig);

    void resume();
    
    void pause();
}
