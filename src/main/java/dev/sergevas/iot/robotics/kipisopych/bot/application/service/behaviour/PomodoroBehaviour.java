package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class PomodoroBehaviour {

    @ConfigProperty(name = "arms.left.up.steps")
    int leftUpSteps;
    @ConfigProperty(name = "arms.left.down.steps")
    int leftDownSteps;
    @ConfigProperty(name = "arms.right.up.steps")
    int rightUpSteps;
    @ConfigProperty(name = "arms.right.down.steps")
    int rightDownSteps;
    @Inject
    ArmMoveInitiator armMoveInitiator;
    @Inject
    VoiceSynthesizer voiceSynthesizer;
    @Inject
    FacialController facialController;

    public void afterSetup() {
        Log.debug("Enter afterSetup behaviour");
        armMoveInitiator.moveBoth(leftUpSteps, rightUpSteps)
                .chain(() -> Uni.combine().all().unis(voiceSynthesizer.speak(VOICE_FILE_NAME),
                                facialController.simulateTalkingFace(50, 10))
                        .asTuple())
                .chain(() -> armMoveInitiator.moveBoth(leftDownSteps, rightDownSteps))
                .subscribe().with(
                        unused -> Log.info("Success exit afterSetup behaviour"),
                        failure -> Log.error("Failed to fire afterSetup behaviour", failure));
        Log.debug("Enter afterSetup behaviour");
    }
}
