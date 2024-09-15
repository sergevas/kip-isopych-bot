package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AstroBehaviour {

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

    public void success(Integer number) {
        Log.infof("Success behaviour, number of astros: %d started", number);
        var voiceFileName = "astro" + number;
        armMoveInitiator.moveRight(rightUpSteps)
                .chain(() -> voiceSynthesizer.speak(voiceFileName))
                .chain(() -> armMoveInitiator.moveRight(rightDownSteps))
                .subscribe().with(
                        unused -> Log.infof("Success Astro behaviour, number of astros: %d ended", number),
                        failure -> Log.error("Failed to fire success Astro behaviour", failure));
    }

    public void failedToCheck() {
        Log.debug("Unable to check astros");
    }
}
