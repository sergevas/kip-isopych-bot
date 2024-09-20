package dev.sergevas.iot.robotics.kipisopych.bot.application.service.behaviour;

import io.smallrye.mutiny.Uni;

import java.time.Duration;

public class Delay {

    public static Uni<Void> delay(long mills) {
        return Uni.createFrom().voidItem().onItem().delayIt().by(Duration.ofMillis(mills));
    }
}
