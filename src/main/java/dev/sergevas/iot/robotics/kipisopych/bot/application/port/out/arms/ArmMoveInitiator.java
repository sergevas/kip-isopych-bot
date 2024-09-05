package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms;

public interface ArmMoveInitiator {

    void moveLeft();

    void moveRight();

    void moveBoth();
}
