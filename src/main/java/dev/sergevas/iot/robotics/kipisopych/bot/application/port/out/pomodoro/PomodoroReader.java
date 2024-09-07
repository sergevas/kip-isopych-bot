package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;

import java.util.Optional;

public interface PomodoroReader {

    Optional<Pomodoro> read();
}
