package dev.sergevas.iot.robotics.kipisopych.bot.domain.arm;

public enum ArmPosition {
    
    MIDDLE(150),
    LEFT_UP(50),
    LEFT_DOWN(250),
    RIGHT_UP(250),
    RIGHT_DOWN(50);

    private final int steps;

    ArmPosition(int steps) {
        this.steps = steps;
    }

    public int steps() {
        return steps;
    }
}
