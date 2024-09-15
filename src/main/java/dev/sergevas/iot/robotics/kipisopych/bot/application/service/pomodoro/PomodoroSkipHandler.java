package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import dev.sergevas.iot.robotics.kipisopych.bot.application.service.BootHook;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled.SkipPredicate;
import io.quarkus.scheduler.ScheduledExecution;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroSkipHandler implements SkipPredicate {

    @Inject
    PomodoroReader pomodoroReader;
    @Inject
    BootHook bootHook;

    @Override
    public boolean test(ScheduledExecution execution) {
        bootHook.bootUp();
        Log.debug("Test scheduled execution skip criteria");
        var pomodoroOpt = pomodoroReader.read();
        var shouldSkipExecution = pomodoroOpt.isEmpty() || pomodoroOpt.get().isPaused();
        Log.debugf("Should skip execution: %s", shouldSkipExecution);
        return shouldSkipExecution;
    }
}
