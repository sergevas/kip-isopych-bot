package dev.sergevas.iot.robotics.kipisopych.bot.application.port.in.event;

public interface EventHandler<T> {

    void handle(T payload);
}
