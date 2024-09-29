package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmDirection;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmDirection.RIGHT_UP_TO_DOWN;

@ApplicationScoped
public class AstroBehaviour {

    @Inject
    ArmMoveInitiator armMoveInitiator;
    @Inject
    VoiceSynthesizer voiceSynthesizer;
    @Inject
    FacialController facialController;

    public void success(Integer number) {
        Log.infof("Success behaviour, number of astros: %d started", number);
        var voiceFileName = "astro" + number;
        armMoveInitiator.moveRight(ArmDirection.RIGHT_DOWN_TO_UP)
                .chain(() -> Uni.combine().all().unis(voiceSynthesizer.speak(voiceFileName),
                                facialController.simulateTalkingFace(50, 20))
                        .asTuple())
                .chain(() -> armMoveInitiator.moveRight(RIGHT_UP_TO_DOWN))
                .subscribe().with(
                        unused -> Log.infof("Success Astro behaviour, number of astros: %d ended", number),
                        failure -> Log.error("Failed to fire success Astro behaviour", failure));
    }

    public void failedToCheck() {
        Log.debug("Unable to check astros");
    }
}
