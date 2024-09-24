package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.web.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.behaviour.BehaviourUseCase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("behaviour")
@Tag(name = "Всякое поведение Исопыча", description = """
   Здесь можно покомандовать Исопычем и принудительно позапускать его разнообразное поведение.""")
public class BehaviourResource {

    @Inject
    BehaviourUseCase behaviourUseCase;

    @GET
    @Path("startup")
    public Response startup() {
        behaviourUseCase.startUp();
        return Response.ok().build();
    }
}
