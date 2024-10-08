package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled.SkipPredicate;
import io.quarkus.scheduler.ScheduledExecution;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroSkipHandler implements SkipPredicate {

    @Inject
    PomodoroReader pomodoroReader;

    @Override
    public boolean test(ScheduledExecution execution) {
        Log.debug("Test scheduled execution skip criteria");
        var pomodoroOpt = pomodoroReader.read();
        var shouldSkipExecution = pomodoroOpt.isEmpty() || pomodoroOpt.get().isPaused();
        Log.debugf("Should skip execution: %s", shouldSkipExecution);
        return shouldSkipExecution;
    }
}
