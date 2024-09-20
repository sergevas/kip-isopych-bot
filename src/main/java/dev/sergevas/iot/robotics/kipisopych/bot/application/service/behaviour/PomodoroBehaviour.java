package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms.ArmMoveInitiator;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour.Delay.delay;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.arm.ArmPosition.MIDDLE;

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
        Uni.combine().all().unis(armMoveInitiator.moveBoth(MIDDLE.getSteps(), MIDDLE.getSteps())
                                .chain(() -> delay(400))
                                .chain(() -> armMoveInitiator.moveBoth(leftUpSteps, rightUpSteps))
                                .chain(() -> delay(3000))
                                .chain(() -> armMoveInitiator.moveBoth(MIDDLE.getSteps(), MIDDLE.getSteps()))
                                .chain(() -> delay(1200))
                                .chain(() -> armMoveInitiator.moveBoth(leftDownSteps, rightDownSteps)),
                        voiceSynthesizer.speak("pomodoroNew"),
                        facialController.simulateTalkingFace(75, 17))
                .asTuple()
                .subscribe().with(
                        unused -> Log.info("Success exit afterSetup behaviour"),
                        failure -> Log.error("Failed to fire afterSetup behaviour", failure));
        Log.debug("Exit afterSetup behaviour");
    }
}
