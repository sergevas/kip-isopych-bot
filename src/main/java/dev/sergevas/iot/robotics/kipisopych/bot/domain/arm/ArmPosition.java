package dev.sergevas.iot.robotics.kipisopych.bot.domain.arm;

public enum ArmPosition {
    
    MIDDLE(150);

    private final int steps;

    ArmPosition(int steps) {
        this.steps = steps;
    }

    public int getSteps() {
        return steps;
    }
}
