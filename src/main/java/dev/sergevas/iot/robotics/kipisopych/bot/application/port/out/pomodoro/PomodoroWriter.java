package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;

public interface PomodoroWriter {

    void write(Pomodoro pomodoro);
}
