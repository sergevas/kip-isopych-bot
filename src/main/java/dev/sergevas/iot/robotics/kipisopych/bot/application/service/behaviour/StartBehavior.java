package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmPosition.*;

@ApplicationScoped
public class StartBehavior {

    @Inject
    ArmMoveInitiator armMoveInitiator;
    @Inject
    VoiceSynthesizer voiceSynthesizer;
    @Inject
    FacialController facialController;

    public void start() {
        Log.debug("Start behaviour started");
        voiceSynthesizer.speak("silence")
                .chain(() -> armMoveInitiator.moveBoth(LEFT_UP.steps(), RIGHT_UP.steps()))
                .chain(() -> Uni.combine().all().unis(voiceSynthesizer.speak("start"),
                                facialController.simulateTalkingFace(75, 12))
                        .asTuple())
                .chain(() -> armMoveInitiator.moveBoth(LEFT_DOWN.steps(), RIGHT_DOWN.steps()))
                .subscribe().with(
                        unused -> Log.info("Success Start behaviour ended"),
                        failure -> Log.error("Failed to fire Start behaviour", failure));
    }
}
