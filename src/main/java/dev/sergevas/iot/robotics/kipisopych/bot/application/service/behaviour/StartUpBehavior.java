package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.behaviour.BehaviourUseCase;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmPosition.*;

@ApplicationScoped
public class StartUpBehavior implements BehaviourUseCase {

    @Inject
    ArmMoveInitiator armMoveInitiator;
    @Inject
    VoiceSynthesizer voiceSynthesizer;
    @Inject
    FacialController facialController;

    public void startUp() {
        Log.debug("Enter StartUp behaviour");
        voiceSynthesizer.speak("silence")
                .chain(() -> armMoveInitiator.moveBoth(LEFT_UP.steps(), RIGHT_UP.steps()))
                .chain(() -> Uni.combine().all().unis(voiceSynthesizer.speak("start"),
                                facialController.simulateTalkingFace(75, 12))
                        .asTuple())
                .chain(() -> armMoveInitiator.moveBoth(LEFT_DOWN.steps(), RIGHT_DOWN.steps()))
                .subscribe().with(
                        unused -> Log.info("Success StartUp behaviour ended"),
                        failure -> Log.error("Failed to fire StartUp behaviour", failure));
    }
}
