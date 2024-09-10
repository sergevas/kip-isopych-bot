package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AstroBehaviour {

    @Inject
    VoiceSynthesizer voiceSynthesizer;

    public void success(Integer number) {
        Log.debugf("Success behaviour, number of astros: %d", number);
        var voiceFileName = "astro" + number;
        voiceSynthesizer.speak(voiceFileName);

    }

    public void failedToCheck() {
        Log.debug("Unable to check astros");

    }
}
