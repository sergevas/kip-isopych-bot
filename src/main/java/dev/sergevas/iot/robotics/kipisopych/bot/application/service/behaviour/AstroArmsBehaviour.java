package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AstroArmsBehaviour {

    @ConfigProperty(name = "arms.left.up.steps")
    int leftUpSteps;
    @ConfigProperty(name = "arms.left.down.steps")
    int leftDownSteps;
    @ConfigProperty(name = "arms.right.up.steps")
    int rightUpSteps;
    @ConfigProperty(name = "arms.right.down.steps")
    int rightDownSteps;

    @Inject
    ArmMoveInitiator armMoveInitiator;

    public void success() {
        Log.debug("Success behaviour started");
        armMoveInitiator.moveBoth(leftUpSteps, rightUpSteps);
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            Log.error(e);
        }
        armMoveInitiator.moveBoth(leftDownSteps, rightDownSteps);
        Log.debug("Success behaviour finished");
    }

    public void failedToCheck() {
        Log.debug("Unable to check astros");

    }
}
