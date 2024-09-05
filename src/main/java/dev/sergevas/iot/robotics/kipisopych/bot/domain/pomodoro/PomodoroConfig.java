package dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro;

public record PomodoroConfig(Integer pomodoroDuration, Integer shortBreakDuration,
                             Integer longBreakDuration, Integer numOfPomodoros) {
}
