package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.web;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.astro.AstroUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("astros")
@Tag(name = "Астронавты и космонавты", description = "Исопыч говорит, сколько астронавтов и космонавтов на орбите прямо сейчас")
public class AstroResource {

    @Inject
    AstroUseCase astroUseCase;

    @GET
    @Operation(
        summary = "Спросить Исопыча",
        description = "Спросить Исопыча о количестве астронавтов и космонавтов на орбите в текущий момент времени"
    )
    public Response getAstronauts() {
        astroUseCase.checkAstronautsOnOrbitAndShoutAboutIt();
        return Response.ok().build();
    }
}
