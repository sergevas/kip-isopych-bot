package dev.sergevas.iot.robotics.kipisopych.bot.domain.astro;

public record Astronaut(int id, String name, String country, String position, String spacecraft, long launched,
                        boolean iss, String url) {
}
