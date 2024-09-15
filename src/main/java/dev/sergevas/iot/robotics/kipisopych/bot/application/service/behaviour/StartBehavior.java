package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class StartBehavior {

    private static final Logger log = LoggerFactory.getLogger(StartBehavior.class);
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

    private static final String VOICE_FILE_NAME = "start";

    @PostConstruct
    public void start() {
        Log.debug("Start behaviour started");
        armMoveInitiator.moveBoth(leftUpSteps, rightUpSteps)
                .chain(() -> voiceSynthesizer.speak(VOICE_FILE_NAME))
                .chain(() -> armMoveInitiator.moveBoth(leftDownSteps, rightDownSteps))
                .subscribe().with(
                        unused -> Log.info("Success Start behaviour, number of astros: %d ended"),
                        failure -> Log.error("Failed to fire Start behaviour", failure));
    }

    public void ping() {
//        Do nothing
    }
}
