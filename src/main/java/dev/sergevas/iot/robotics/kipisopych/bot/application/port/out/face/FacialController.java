package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face;

public interface FacialController {

    void simulateTalkingFace(int sleep, int time);

    void blinkRightEye(double speed);

    void blinkBothEyes(double speed);

    void moveMouth(double speed);
}
