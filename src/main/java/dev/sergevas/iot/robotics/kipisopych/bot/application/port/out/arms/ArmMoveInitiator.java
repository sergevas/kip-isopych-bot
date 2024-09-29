package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmDirection;
import io.smallrye.mutiny.Uni;

public interface ArmMoveInitiator {

    Uni<Void> moveLeft(ArmDirection armDirection);

    Uni<Void> moveRight(ArmDirection armDirection);

    Uni<Void> moveBoth(ArmDirection lArmDirection, ArmDirection rArmDirection);
}
