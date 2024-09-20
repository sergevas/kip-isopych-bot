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
public class AstroBehaviour {

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

    public void success(Integer number) {
        Log.infof("Success behaviour, number of astros: %d started", number);
        var voiceFileName = "astro" + number;
        armMoveInitiator.moveRight(rightUpSteps)
                .chain(() -> Uni.combine().all().unis(voiceSynthesizer.speak(voiceFileName),
                                facialController.simulateTalkingFace(50, 20))
                        .asTuple())
                .chain(() -> armMoveInitiator.moveRight(rightDownSteps))
                .subscribe().with(
                        unused -> Log.infof("Success Astro behaviour, number of astros: %d ended", number),
                        failure -> Log.error("Failed to fire success Astro behaviour", failure));
    }

    public void failedToCheck() {
        Log.debug("Unable to check astros");
    }
}
