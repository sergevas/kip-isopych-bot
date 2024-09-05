package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.arm;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockArmAdapter implements ArmMoveInitiator {

    @Override
    public void moveLeft() {
        Log.info("Kip Isopych is moving the left arm");
    }

    @Override
    public void moveRight() {
        Log.info("Kip Isopych is moving the right arm");
    }

    @Override
    public void moveBoth() {
        Log.info("Kip Isopych is moving the both arms");
    }
}
