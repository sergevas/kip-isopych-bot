package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.face;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockFaceAdapter implements FacialController {

    @Override
    public void simulateTalkingFace(int sleep, int time) {
        Log.infof("Kip Isopych is simulating talking face sleep %d time %d", sleep, time);
    }

    @Override
    public void blinkRightEye(double speed) {
        Log.infof("Kip Isopych is blinking with the right eye with speed %f", speed);
    }

    @Override
    public void blinkBothEyes(double speed) {
        Log.infof("Kip Isopych is blinking with his both eyes with speed %f", speed);
    }

    @Override
    public void moveMouth(double speed) {
        Log.infof("Kip Isopych is moving his mouth with speed %f", speed);
    }
}
