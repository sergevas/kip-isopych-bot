package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.face;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@IfBuildProfile("prod")
public class RpiFaceAdapter implements FacialController {

    @Override
    public void blinkLeftEye(double speed) {

    }

    @Override
    public void blinkRightEye(double speed) {

    }

    @Override
    public void blinkBothEyes(double speed) {

    }

    @Override
    public void moveMouth(double speed) {

    }
}
