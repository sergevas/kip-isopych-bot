package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.voice;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizer;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice.VoiceSynthesizerException;
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
//@IfBuildProfile("prod")
public class RpiVoiceAdapter implements VoiceSynthesizer {

    @ConfigProperty(name = "voice.base.path")
    String voiceBasePath;

    @Override
    public void speak(String fileName) {
        var absPath = voiceBasePath + "/" + fileName;
//        playMp3(absPath);
        playAudio(absPath);
    }

    public Uni<Void> playMp3(String filePath) {
        Log.debugf("filepath: %s", filePath);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
                // Открытие MP3-файла для воспроизведения
                InputStream fileInputStream = new FileInputStream(filePath);
                Log.debugf("Play mp3 file: %s", filePath);
                AdvancedPlayer player = new AdvancedPlayer(fileInputStream, FactoryRegistry.systemRegistry().createAudioDevice());
                // Воспроизведение MP3-файла
                player.play();
            } catch (Exception e) {
                Log.error(e);
                throw new VoiceSynthesizerException(e);
            }
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    public void playAudio(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            AdvancedPlayer player = new AdvancedPlayer(fileInputStream, FactoryRegistry.systemRegistry().createAudioDevice());
            player.play();
        } catch (Exception e) {
            throw new VoiceSynthesizerException(e);
        }
    }
}
