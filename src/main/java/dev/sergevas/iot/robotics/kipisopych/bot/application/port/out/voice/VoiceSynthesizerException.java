package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.voice;

import dev.sergevas.iot.robotics.kipisopych.bot.application.KipIsopychBaseException;

public class VoiceSynthesizerException extends KipIsopychBaseException {

    public VoiceSynthesizerException() {
    }

    public VoiceSynthesizerException(Exception e) {
        super(e);
    }
}
