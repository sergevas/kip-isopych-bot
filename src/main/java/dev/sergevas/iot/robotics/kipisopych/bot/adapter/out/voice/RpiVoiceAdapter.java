package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.voice;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizerException;
import io.quarkus.arc.profile.UnlessBuildProfile;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.FileInputStream;
import java.io.InputStream;

@ApplicationScoped
@UnlessBuildProfile("test")
public class RpiVoiceAdapter implements VoiceSynthesizer {

    @ConfigProperty(name = "voice.base.path")
    String voiceBasePath;

    @Override
    public void speak(String fileName) {
        Log.debug("Enter speak method...");
        var absPath = voiceBasePath + "/" + fileName + ".mp3";
        playMp3(absPath).subscribe().with(
                unused -> Log.debugf("MP3 file %s playback completed", absPath),
                failure -> Log.errorf(failure, "Failed to play MP3 file %s", absPath)
        );
        Log.debug("Exit speak method...");
    }

    public Uni<Void> playMp3(String filePath) {
        Log.debugf("filepath: %s", filePath);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
                InputStream fileInputStream = new FileInputStream(filePath);
                Log.debugf("Play MP3 file: %s", filePath);
                AdvancedPlayer player = new AdvancedPlayer(fileInputStream, FactoryRegistry.systemRegistry().createAudioDevice());
                player.play();
            } catch (Exception e) {
                Log.error(e);
                throw new VoiceSynthesizerException(e);
            }
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }
}
