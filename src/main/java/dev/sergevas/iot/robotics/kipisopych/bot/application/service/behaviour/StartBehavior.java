package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class StartBehavior {

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

    @PostConstruct
    public void start() {
        Log.debug("Start behaviour started");
        voiceSynthesizer.speak("silence")
                .chain(() -> armMoveInitiator.moveBoth(leftUpSteps, rightUpSteps))
                .chain(() -> Uni.combine().all().unis(voiceSynthesizer.speak("start"),
                                facialController.simulateTalkingFace(75, 12))
                        .asTuple())
                .chain(() -> armMoveInitiator.moveBoth(leftDownSteps, rightDownSteps))
                .subscribe().with(
                        unused -> Log.info("Success Start behaviour ended"),
                        failure -> Log.error("Failed to fire Start behaviour", failure));
    }

    public void ping() {
//        Do nothing
    }
}
