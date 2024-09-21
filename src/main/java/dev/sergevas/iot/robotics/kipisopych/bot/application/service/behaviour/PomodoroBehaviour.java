package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.Delay.delay;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmPosition.*;

@ApplicationScoped
public class PomodoroBehaviour {

    @Inject
    ArmMoveInitiator armMoveInitiator;
    @Inject
    VoiceSynthesizer voiceSynthesizer;
    @Inject
    FacialController facialController;

    public void setup() {
        Log.debug("Enter setup behaviour");
        Uni.combine().all().unis(armMoveInitiator.moveBoth(MIDDLE.steps(), MIDDLE.steps())
                                .chain(() -> delay(400))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_UP.steps(), RIGHT_UP.steps()))
                                .chain(() -> delay(3000))
                                .chain(() -> armMoveInitiator.moveBoth(MIDDLE.steps(), MIDDLE.steps()))
                                .chain(() -> delay(1200))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_DOWN.steps(), RIGHT_DOWN.steps())),
                        voiceSynthesizer.speak("pomodoroNew"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .subscribe().with(
                        unused -> Log.info("Success exit setup behaviour"),
                        failure -> Log.error("Failed to fire setup behaviour", failure));
        Log.debug("Exit setup behaviour");
    }

    public void shortBreak() {
        Log.debug("Enter shortBreak behaviour");
        Uni.combine().all().unis(armMoveInitiator.moveRight(MIDDLE.steps())
                                .chain(() -> delay(1000))
                                .chain(() -> armMoveInitiator.moveRight(RIGHT_DOWN.steps())),
                        voiceSynthesizer.speak("shortBreak"),
                        facialController.simulateTalkingFace(75, 3))
                .asTuple()
                .subscribe().with(
                        unused -> Log.info("Success exit shortBreak behaviour"),
                        failure -> Log.error("Failed to fire shortBreak behaviour", failure));
        Log.debug("Exit shortBreak behaviour");
    }

    public void longBreak() {
        Log.debug("Enter longBreak behaviour");
        Uni.combine().all().unis(armMoveInitiator.moveBoth(MIDDLE.steps(), MIDDLE.steps())
                                .chain(() -> delay(400))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_UP.steps(), RIGHT_UP.steps()))
                                .chain(() -> delay(3000))
                                .chain(() -> armMoveInitiator.moveBoth(MIDDLE.steps(), MIDDLE.steps()))
                                .chain(() -> delay(1200))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_DOWN.steps(), RIGHT_DOWN.steps())),
                        voiceSynthesizer.speak("longBreak"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .subscribe().with(
                        unused -> Log.info("Success exit longBreak behaviour"),
                        failure -> Log.error("Failed to fire longBreak behaviour", failure));
        Log.debug("Exit longBreak behaviour");
    }

    public void pause() {
        Log.debug("Enter pause behaviour");
        Uni.combine().all().unis(voiceSynthesizer.speak("pomodoroPaused"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .subscribe().with(
                        unused -> Log.info("Success exit pause behaviour"),
                        failure -> Log.error("Failed to fire pause behaviour", failure));
        Log.debug("Exit pause behaviour");
    }

    public void resume() {
        Log.debug("Enter resume behaviour");
        Uni.combine().all().unis(voiceSynthesizer.speak("pomodoroResumed"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .subscribe().with(
                        unused -> Log.info("Success exit resume behaviour"),
                        failure -> Log.error("Failed to fire resume behaviour", failure));
        Log.debug("Exit resume behaviour");
    }
}
