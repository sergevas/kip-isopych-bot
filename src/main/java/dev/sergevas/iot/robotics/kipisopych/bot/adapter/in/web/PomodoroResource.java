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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("pomodoro")
@Tag(name = "Помодоро таймер", description = """
   Здесь находятся настройки помодора таймера Исопыча.
   Таймер можно запустить, поставить на паузу и запустить заново.
   Количество помодоров, длительность одного помодоро, длительность короткого перерыва,
   длительность длинного перерыва - всё-всё-всё настраивается у Исопыча.""")
public class PomodoroResource {

    @Inject
    PomodoroControlUseCase pomodoroControlUseCase;

    @POST
    @Path("setup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Установить или обновить таймер",
            description = """
                    Здесь можно устанвить таймер.
                    Исопычу без разницы, были ли до этого уже установлен таймер.
                    Он начнет отсчет заново."""
    )
    public Response setupPomodoro(@RequestBody(description = "Это - конфигурация таймера", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(name = "pomodoroConfig", type = SchemaType.OBJECT, required = true,
                            implementation = PomodoroConfig.class),
                    example = """
                    {
                      "pomodoroDuration": 25,
                      "shortBreakDuration": 5,
                      "longBreakDuration": 15,
                      "numOfPomodoros": 4
                    }
                    """))PomodoroConfig pomodoroConfig) {
        pomodoroControlUseCase.setup(pomodoroConfig);
        return Response.ok().build();
    }

    @PUT
    @Path("resume")
    @Operation(
            summary = "Продолжить таймер",
            description = "Таймер продолжит отсчитывать время с того места, на котором остановился. С точностью до минуты."
    )
    public Response resumePomodoro() {
        pomodoroControlUseCase.resume();
        return Response.ok().build();
    }

    @PUT
    @Path("pause")
    @Operation(
            summary = "Поставить таймер на паузу",
            description = "Исопыч запомнит момент останвки таймера. Но только до рестарта. После рестарта он всё забудет."
    )
    public Response pausePomodoro() {
        pomodoroControlUseCase.pause();
        return Response.ok().build();
    }
}
