package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.voice;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;

//@ApplicationScoped
//@DefaultBean
public class MockVoiceAdapter implements VoiceSynthesizer {

    @Override
    public void speak(String fileName) {
        Log.debugf("********** Kip Isopych says: %s", fileName);
    }
}
