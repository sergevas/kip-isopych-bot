package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.web;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.astro.AstroUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("astros")
public class AstroResource {

    @Inject
    AstroUseCase astroUseCase;

    @GET
    public Response getAstronauts() {
        astroUseCase.checkAstronautsOnOrbitAndShoutAboutIt();
        return Response.ok().build();
    }
}
