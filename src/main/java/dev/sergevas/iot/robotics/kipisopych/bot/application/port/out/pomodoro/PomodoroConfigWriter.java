package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroConfig;

public interface PomodoroConfigWriter {

    void write(PomodoroConfig config);
}
