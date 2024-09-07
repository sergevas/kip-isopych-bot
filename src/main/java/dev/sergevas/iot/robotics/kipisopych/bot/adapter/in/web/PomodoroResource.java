package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.web;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.pomodoro.PomodoroControlUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pomodoro")
public class PomodoroResource {

    @Inject
    PomodoroControlUseCase pomodoroControlUseCase;

    @POST
    @Path("setup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setupPomodoro(PomodoroConfig pomodoroConfig) {
        pomodoroControlUseCase.setup(pomodoroConfig);
        return Response.ok().build();
    }

    @PUT
    @Path("resume")
    public Response resumePomodoro() {
        pomodoroControlUseCase.resume();
        return Response.ok().build();
    }

    @PUT
    @Path("pause")
    public Response pausePomodoro() {
        pomodoroControlUseCase.pause();
        return Response.ok().build();
    }
}
