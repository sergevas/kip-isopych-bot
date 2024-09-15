package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.arm;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockArmAdapter implements ArmMoveInitiator {

    @Override
    public Uni<Void> moveLeft(int steps) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.infof("Kip Isopych is moving the left arm for %d steps", steps);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> moveRight(int steps) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.infof("Kip Isopych is moving the right arm for %d steps", steps);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> moveBoth(int lSteps, int rSteps) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.infof("Kip Isopych is moving the both arms for %d lSteps and %d for rSteps", lSteps, rSteps);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }
}
