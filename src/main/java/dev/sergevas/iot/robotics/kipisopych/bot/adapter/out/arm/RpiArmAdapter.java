package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.arm;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveException;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmDirection;
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

    @ConfigProperty(name = "arms.enabled")
    boolean enabled;

    @Override
    public Uni<Void> moveLeft(ArmDirection armDirection) {
        return moveArm(leftSide, armDirection);
    }

    @Override
    public Uni<Void> moveRight(ArmDirection armDirection) {
        return moveArm(rightSide, armDirection);
    }

    @Override
    public Uni<Void> moveBoth(ArmDirection lArmDirection, ArmDirection rArmDirection) {
        if (!enabled) {
            Log.debug("Arms are disabled");
            return Uni.createFrom().nullItem();
        }
        Log.debugf("Move both arms for %s lArmDirection and %s rArmDirection started", lArmDirection, rArmDirection);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            for (int i = 0; i < lArmDirection.getPositions().length; i++) {
                var lSteps = lArmDirection.getPositions()[i];
                var rSteps = rArmDirection.getPositions()[i];
                var command = new String[]{"/bin/sh", "-c", "echo " + leftSide + "=" + lSteps + " > /dev/servoblaster;"
                        + "echo " + rightSide + "=" + rSteps + " > /dev/servoblaster"};
                startProcessBuilder(command);
                Thread.sleep(50);
            }
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    public Uni<Void> moveArm(String side, ArmDirection armDirection) {
        if (!enabled) {
            Log.debug("Arms are disabled");
            return Uni.createFrom().nullItem();
        }
        Log.debugf("Move %s side arm for %s arm direction started", side, armDirection);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            for (int steps : armDirection.getPositions()) {
                var command = new String[]{"/bin/sh", "-c", "echo " + side + "=" + steps + " > /dev/servoblaster"};
                startProcessBuilder(command);
                Thread.sleep(50);
            }
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
