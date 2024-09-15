package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.arm;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveException;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Arrays;

@ApplicationScoped
@IfBuildProfile("prod")
public class RpiArmAdapter implements ArmMoveInitiator {

    @ConfigProperty(name = "arms.left.side")
    String leftSide;

    @ConfigProperty(name = "arms.right.side")
    String rightSide;

    @Override
    public Uni<Void> moveLeft(int steps) {
        return moveArm(leftSide, steps);
    }

    @Override
    public Uni<Void> moveRight(int steps) {
        return moveArm(rightSide, steps);
    }

    @Override
    public Uni<Void> moveBoth(int lSteps, int rSteps) {
        Log.debugf("Move both arms for %d lSteps and %d rSteps started", lSteps, rSteps);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            var command = new String[]{"/bin/sh", "-c", "echo " + leftSide + "=" + lSteps + " > /dev/servoblaster;"
                    + "echo " + rightSide + "=" + rSteps + " > /dev/servoblaster"};
            startProcessBuilder(command);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    public Uni<Void> moveArm(String side, int steps) {
        Log.debugf("Move %s side arm for %d steps started", side, steps);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            var command = new String[]{"/bin/sh", "-c", "echo " + side + "=" + steps + " > /dev/servoblaster"};
            startProcessBuilder(command);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    private void startProcessBuilder(String[] command) {
        Log.debugf("Have got arm command: %s", Arrays.toString(command));
        var processBuilder = new ProcessBuilder().command(command);
        try {
            processBuilder.start();
        } catch (Exception e) {
            Log.error(e);
            throw new ArmMoveException(e);
        }
    }
}
