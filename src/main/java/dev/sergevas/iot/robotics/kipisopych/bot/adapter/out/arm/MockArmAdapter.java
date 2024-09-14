package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.arm;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockArmAdapter implements ArmMoveInitiator {

    @Override
    public void moveLeft(int steps) {
        Log.infof("Kip Isopych is moving the left arm for %d steps", steps);
    }

    @Override
    public void moveRight(int steps) {
        Log.infof("Kip Isopych is moving the right arm for %d steps", steps);
    }

    @Override
    public void moveBoth(int lSteps, int rSteps) {
        Log.infof("Kip Isopych is moving the both arms for %d lSteps and %d for rSteps", lSteps, rSteps);
    }
}
