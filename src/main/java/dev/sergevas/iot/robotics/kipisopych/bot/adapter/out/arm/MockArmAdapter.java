package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.arm;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmDirection;
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
    public Uni<Void> moveLeft(ArmDirection armDirection) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.infof("Kip Isopych is moving to direction", armDirection);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> moveRight(ArmDirection armDirection) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.infof("Kip Isopych is moving to direction", armDirection);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> moveBoth(ArmDirection lArmDirection, ArmDirection rArmDirection) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.infof("Kip Isopych is moving the both arms for %s lArmDirection and %s for rArmDirection",
                    lArmDirection, rArmDirection);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }
}
