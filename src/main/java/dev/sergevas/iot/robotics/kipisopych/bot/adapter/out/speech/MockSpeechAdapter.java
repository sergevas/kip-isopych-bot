package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.speech;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.speech.SpeechSynthesizer;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockSpeechAdapter implements SpeechSynthesizer {

    @Override
    public void speak(String text) {
        Log.debugf("********** Kip Isopych says: %s", text);
    }
}
