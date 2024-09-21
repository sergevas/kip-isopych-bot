package dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.event;

public interface EventSender<T> {

    void send(T payload);
}
