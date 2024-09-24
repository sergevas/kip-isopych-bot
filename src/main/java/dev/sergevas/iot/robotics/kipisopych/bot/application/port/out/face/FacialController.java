package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face;

import io.smallrye.mutiny.Uni;

public interface FacialController {

    Uni<Void> simulateTalkingFace(long sleep, int time);

    Uni<Void> actOnStartup(long sleep, int time);

    Uni<Void> displayBCD(int valueToDisplay);

    void blinkRightEye(double speed);

    void blinkBothEyes(double speed);

    void moveMouth(double speed);
}
