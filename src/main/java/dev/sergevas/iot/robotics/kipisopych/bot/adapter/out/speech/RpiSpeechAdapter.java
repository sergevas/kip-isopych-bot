package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.speech;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.speech.SpeechSynthesizer;
import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@IfBuildProfile("prod")
public class RpiSpeechAdapter implements SpeechSynthesizer {

    @Override
    public void speak(String text) {
    }
}
