package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms;

import io.smallrye.mutiny.Uni;

public interface ArmMoveInitiator {

    Uni<Void> moveLeft(int steps);

    Uni<Void> moveRight(int steps);

    Uni<Void> moveBoth(int lSteps, int rSteps);
}
