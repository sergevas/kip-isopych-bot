package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.web;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroSetupUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pomodoro")
public class PomodoroResource {

    @Inject
    PomodoroSetupUseCase pomodoroSetupUseCase;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setupPomodoro(PomodoroConfig pomodoroConfig) {
        pomodoroSetupUseCase.setup(pomodoroConfig);
        return Response.noContent().build();
    }
}
