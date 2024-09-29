package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroType;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.Delay.delay;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmDirection.*;

@ApplicationScoped
public class PomodoroBehaviour {

    @Inject
    ArmMoveInitiator armMoveInitiator;
    @Inject
    VoiceSynthesizer voiceSynthesizer;
    @Inject
    FacialController facialController;

    public void setup(Pomodoro pomodoro) {
        Log.debug("Enter setup behaviour");
        Uni.combine().all().unis(armMoveInitiator.moveBoth(LEFT_DOWN_TO_MIDDLE, RIGHT_DOWN_TO_MIDDLE)
                                .chain(() -> delay(400))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_MIDDLE_TO_UP, RIGHT_MIDDLE_TO_UP))
                                .chain(() -> delay(3000))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_UP_TO_MIDDLE, RIGHT_UP_TO_MIDDLE))
                                .chain(() -> delay(1200))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_MIDDLE_TO_DOWN, RIGHT_MIDDLE_TO_DOWN)),
                        voiceSynthesizer.speak("pomodoroNew"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .chain(() -> facialController.renderPomodoroTypeDependantEyes(pomodoro.getType()))
                .chain(() -> facialController.displayBCD(pomodoro.getRemainTime()))
                .subscribe().with(
                        unused -> Log.info("Success exit setup behaviour"),
                        failure -> Log.error("Failed to fire setup behaviour", failure));
        Log.debug("Exit setup behaviour");
    }

    public void shortBreak(Pomodoro pomodoro) {
        Log.debug("Enter shortBreak behaviour");
        Uni.combine().all().unis(voiceSynthesizer.speak("shortBreak"),
                        facialController.simulateTalkingFace(75, 3))
                .asTuple()
                .chain(() -> facialController.renderPomodoroTypeDependantEyes(pomodoro.getType()))
                .chain(() -> facialController.displayBCD(pomodoro.getRemainTime()))
                .subscribe().with(
                        unused -> Log.info("Success exit shortBreak behaviour"),
                        failure -> Log.error("Failed to fire shortBreak behaviour", failure));
        Log.debug("Exit shortBreak behaviour");
    }

    public void longBreak(Pomodoro pomodoro) {
        Log.debug("Enter longBreak behaviour");
        Uni.combine().all().unis(armMoveInitiator.moveRight(RIGHT_DOWN_TO_MIDDLE)
                                .chain(() -> delay(3000))
                                .chain(() -> armMoveInitiator.moveRight(RIGHT_MIDDLE_TO_DOWN)),
                        voiceSynthesizer.speak("longBreak"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .chain(() -> facialController.renderPomodoroTypeDependantEyes(pomodoro.getType()))
                .chain(() -> facialController.displayBCD(pomodoro.getRemainTime()))
                .subscribe().with(
                        unused -> Log.info("Success exit longBreak behaviour"),
                        failure -> Log.error("Failed to fire longBreak behaviour", failure));
        Log.debug("Exit longBreak behaviour");
    }

    public void pause(Pomodoro pomodoro) {
        Log.debug("Enter pause behaviour");
        Uni.combine().all().unis(voiceSynthesizer.speak("pomodoroPaused"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .subscribe().with(
                        unused -> Log.info("Success exit pause behaviour"),
                        failure -> Log.error("Failed to fire pause behaviour", failure));
        Log.debug("Exit pause behaviour");
    }

    public void resume(Pomodoro pomodoro) {
        Log.debug("Enter resume behaviour");
        Uni.combine().all().unis(voiceSynthesizer.speak("pomodoroResumed"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .chain(() -> facialController.renderPomodoroTypeDependantEyes(pomodoro.getType()))
                .chain(() -> facialController.displayBCD(pomodoro.getRemainTime()))
                .subscribe().with(
                        unused -> Log.info("Success exit resume behaviour"),
                        failure -> Log.error("Failed to fire resume behaviour", failure));
        Log.debug("Exit resume behaviour");
    }

    public void tick(int time, PomodoroType type) {
        Log.debugf("Enter tick behaviour time=%d type=%s", time, type);
        facialController.displayBCD(time)
                .subscribe().with(
                        unused -> Log.info("Success exit tick behaviour"),
                        failure -> Log.error("Failed to fire tick behaviour", failure));
        Log.debug("Exit setup behaviour");
    }
}
