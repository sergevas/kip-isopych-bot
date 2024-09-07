package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.KipIsopychBaseException;

public class PomodoroException extends KipIsopychBaseException {

    public PomodoroException() {
    }

    public PomodoroException(Exception e) {
        super(e);
    }
}
