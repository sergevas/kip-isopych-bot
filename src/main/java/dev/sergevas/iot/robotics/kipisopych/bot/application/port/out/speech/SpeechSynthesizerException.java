package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.speech;

import dev.sergevas.iot.robotics.kipisopych.bot.application.KipIsopychBaseException;

public class SpeechSynthesizerException extends KipIsopychBaseException {

    public SpeechSynthesizerException() {
    }

    public SpeechSynthesizerException(Exception e) {
        super(e);
    }
}
