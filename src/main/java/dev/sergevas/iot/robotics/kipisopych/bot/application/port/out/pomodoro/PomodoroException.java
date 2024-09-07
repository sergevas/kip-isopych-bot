package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.KipIsopychBaseException;

public class PomodoroException extends KipIsopychBaseException {

    public static final String FETCH_ERROR_MSG = "Unable to fetch Pomodoro from DB!";

    public PomodoroException() {
    }

    public PomodoroException(Exception e) {
        super(e);
    }

    public PomodoroException(String s) {
        super(s);
    }
}
