package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice;

import io.smallrye.mutiny.Uni;

public interface VoiceSynthesizer {

    Uni<Void> speak(String fileName);
}
