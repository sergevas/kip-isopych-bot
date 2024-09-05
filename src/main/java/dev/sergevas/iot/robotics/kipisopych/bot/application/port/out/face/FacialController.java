package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face;

public interface FacialController {

    void blinkLeftEye(double speed);

    void blinkRightEye(double speed);

    void blinkBothEyes(double speed);

    void moveMouth(double speed);
}
