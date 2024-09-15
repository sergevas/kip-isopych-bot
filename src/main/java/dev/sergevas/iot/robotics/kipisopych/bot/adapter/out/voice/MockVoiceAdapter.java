package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.voice;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockVoiceAdapter implements VoiceSynthesizer {

    @Override
    public Uni<Void> speak(String fileName) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.debugf("********** Kip Isopych says: %s", fileName);
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }
}
