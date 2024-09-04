package dev.sergevas.iot.robotics.kipisopych.bot.application;

public class KipIsopychBaseException extends RuntimeException {

    public KipIsopychBaseException() {
    }

    public KipIsopychBaseException(String message) {
        super(message);
    }

    public KipIsopychBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public KipIsopychBaseException(Throwable cause) {
        super(cause);
    }

    public KipIsopychBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public KipIsopychBaseException(Exception e) {
    }
}
