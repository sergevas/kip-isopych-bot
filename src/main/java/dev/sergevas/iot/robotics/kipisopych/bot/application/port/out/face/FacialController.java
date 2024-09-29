package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroType;
import io.smallrye.mutiny.Uni;

public interface FacialController {

    Uni<Void> simulateTalkingFace(long sleep, int time);

    Uni<Void> blinkOnStartup();

    Uni<Void> dance();

    Uni<Void> lightsOn();

    Uni<Void> lightsOff();

    Uni<Void> displayBCD(int valueToDisplay);

    Uni<Void> renderPomodoroTypeDependantEyes(PomodoroType pomodoroType);

    void blinkRightEye(double speed);

    void blinkBothEyes(double speed);

    void moveMouth(long sleep, int time);
}
