package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.arms;

import dev.sergevas.iot.robotics.kipisopych.bot.application.KipIsopychBaseException;

public class ArmMoveException extends KipIsopychBaseException {

    public ArmMoveException() {
    }

    public ArmMoveException(Exception e) {
        super(e);
    }
}
