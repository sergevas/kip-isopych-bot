package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.behaviour.BehaviourUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.Delay.delay;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmDirection.*;

@ApplicationScoped
public class BehaviorService implements BehaviourUseCase {

    @Inject
    ArmMoveInitiator armMoveInitiator;
    @Inject
    VoiceSynthesizer voiceSynthesizer;
    @Inject
    FacialController facialController;

    public void startUp() {
        Log.debug("Enter StartUp behaviour");
        voiceSynthesizer.speak("silence")
                .chain(() -> Uni.combine().all().unis(armMoveInitiator.moveBoth(LEFT_DOWN_TO_MIDDLE, RIGHT_DOWN_TO_MIDDLE),
                                facialController.blinkOnStartup())
                        .asTuple())
                .chain(() -> Uni.combine().all().unis(voiceSynthesizer.speak("start"),
                        facialController.blinkOnStartup()).asTuple())
                .chain(() -> armMoveInitiator.moveBoth(LEFT_MIDDLE_TO_DOWN, RIGHT_MIDDLE_TO_DOWN))
                .subscribe().with(
                        unused -> Log.info("Success StartUp behaviour ended"),
                        failure -> Log.error("Failed to fire StartUp behaviour", failure));
    }

    public void dance() {
        Log.debug("Enter Dance behaviour");
        Uni.combine().all().unis(voiceSynthesizer.speak("breakdance"),
                        facialController.dance(),
                        armMoveInitiator.moveBoth(LEFT_DOWN_TO_MIDDLE, RIGHT_DOWN_TO_MIDDLE)
                                .chain(() -> delay(3000))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_MIDDLE_TO_DOWN, RIGHT_MIDDLE_TO_UP))
                                .chain(() -> delay(500))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_DOWN_TO_MIDDLE, RIGHT_UP_TO_MIDDLE))
                                .chain(() -> delay(100))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_MIDDLE_TO_UP, RIGHT_MIDDLE_TO_DOWN))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_UP_TO_MIDDLE, RIGHT_DOWN_TO_MIDDLE))
                                .chain(() -> delay(50))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_MIDDLE_TO_DOWN, RIGHT_MIDDLE_TO_UP))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_UP_TO_MIDDLE, RIGHT_DOWN_TO_MIDDLE))
                                .chain(() -> delay(500))
                                .chain(() -> armMoveInitiator.moveBoth(LEFT_MIDDLE_TO_DOWN, RIGHT_MIDDLE_TO_DOWN))
                ).asTuple()
                .subscribe().with(
                        unused -> Log.info("Success Dance behaviour ended"),
                        failure -> Log.error("Failed to fire Dance behaviour", failure));
    }
}
