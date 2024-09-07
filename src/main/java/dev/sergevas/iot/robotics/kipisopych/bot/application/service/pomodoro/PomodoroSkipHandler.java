package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled.SkipPredicate;
import io.quarkus.scheduler.ScheduledExecution;
import jakarta.inject.Inject;

public class PomodoroSkipHandler implements SkipPredicate {

    @Inject
    PomodoroReader pomodoroReader;

    @Override
    public boolean test(ScheduledExecution execution) {
        Log.debug(execution);
        var pomodoro = pomodoroReader.read();
        return pomodoro.isPaused();
    }
}
