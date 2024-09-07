package dev.sergevas.iot.robotics.kipisopych.bot.application.service.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroStateUseCase;
import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PomodoroTimeService {

    @Inject
    PomodoroStateUseCase pomodoroStateUseCase;

    @Scheduled(every = "60s", skipExecutionIf = PomodoroSkipHandler.class)
    void countdownPomodoro() {
        Log.debug("Fire Pomodoro Timer countdown...");
        pomodoroStateUseCase.updateCurrentState();
    }
}
