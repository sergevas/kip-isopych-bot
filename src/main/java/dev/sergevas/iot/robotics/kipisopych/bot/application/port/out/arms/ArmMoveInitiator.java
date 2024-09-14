package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms;

public interface ArmMoveInitiator {

    void moveLeft(int steps);

    void moveRight(int steps);

    void moveBoth(int lSteps, int rSteps);
}
