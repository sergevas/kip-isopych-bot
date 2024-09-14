package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.arm;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveException;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.Arrays;

@ApplicationScoped
@IfBuildProfile("prod")
public class RpiArmAdapter implements ArmMoveInitiator {

    @ConfigProperty(name = "arms.left.side")
    String leftSide;

    @ConfigProperty(name = "arms.right.side")
    String rightSide;

    @Override
    public void moveLeft(int steps) {
        moveArm(leftSide, steps);
    }

    @Override
    public void moveRight(int steps) {
        moveArm(rightSide, steps);
    }

    @Override
    public void moveBoth(int lSteps, int rSteps) {
        Log.debugf("Move both arms for %d lSteps and %d rSteps started", lSteps, rSteps);
        var command = new String[]{"/bin/sh", "-c", "echo " + leftSide + "=" + lSteps + " > /dev/servoblaster;"
                + "echo " + rightSide + "=" + rSteps + " > /dev/servoblaster"};
        Log.debugf("Have got arm command: %s", Arrays.toString(command));
        var processBuilder = new ProcessBuilder().command(command);
        try {
            processBuilder.start();
        } catch (IOException e) {
            Log.error(e);
            throw new ArmMoveException(e);
        }
        Log.debugf("Move both arms for %d lSteps and %d rSteps finished", lSteps, rSteps);
    }

    private void moveArm(String side, int steps) {
        Log.debugf("Move %s side arm for %d steps started", side, steps);
        var command = new String[]{"/bin/sh", "-c", "echo " + side + "=" + steps + " > /dev/servoblaster"};
        Log.debugf("Have got arm command: %s", Arrays.toString(command));
        var processBuilder = new ProcessBuilder().command(command);
        try {
            processBuilder.start();
        } catch (IOException e) {
            Log.error(e);
            throw new ArmMoveException(e);
        }
        Log.debugf("Move %s side arm for %d steps finished", side, steps);
    }
}
