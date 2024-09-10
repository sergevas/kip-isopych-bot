package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.voice;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockVoiceAdapter implements VoiceSynthesizer {

    @Override
    public void speak(String fileName) {
        Log.debugf("********** Kip Isopych says: %s", fileName);
    }
}
