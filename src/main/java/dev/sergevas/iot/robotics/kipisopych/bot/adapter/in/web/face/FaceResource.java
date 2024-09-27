package dev.sergevas.iot.robotics.kipisopych.bot.adapter.in.web.face;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("behaviour/face")
public class FaceResource {

    @Inject
    FacialController facialController;

    @GET
    @Path("blinkOnStartup")
    public Uni<Void> blinkOnStartup() {
        return facialController.blinkOnStartup();
    }

    @GET
    @Path("lights/on")
    public Uni<Void> lightsOn() {
        return facialController.lightsOn();
    }

    @GET
    @Path("lights/off")
    public Uni<Void> lightsOff() {
        return facialController.lightsOff();
    }

    @PUT
    @Path("blinkBoth")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response blinkBoth(double speed) {
        facialController.blinkBothEyes(speed);
        return Response.ok().build();
    }

    @PUT
    @Path("simulateTalkingFace")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response simulateTalkingFace(FaceDynamics faceDynamics) {
        facialController.simulateTalkingFace(faceDynamics.sleep(), faceDynamics.time());
        return Response.ok().build();
    }

    @PUT
    @Path("blinkRightEye")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response blinkRightEye(double speed) {
        facialController.blinkRightEye(speed);
        return Response.ok().build();
    }
}
