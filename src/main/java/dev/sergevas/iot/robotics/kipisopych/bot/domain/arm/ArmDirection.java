package dev.sergevas.iot.robotics.kipisopych.bot.domain.arm;

public enum ArmDirection {
    LEFT_DOWN_TO_UP(new int[]{240, 235, 230, 225, 220, 215, 210, 205, 200, 195, 190, 185, 180, 175, 170, 165, 160, 155,
            150, 145, 140, 135, 130, 125, 120, 115, 110, 105, 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50}),
    LEFT_UP_TO_DOWN(new int[]{50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 145,
            150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 225, 230, 235, 240}),
    LEFT_DOWN_TO_MIDDLE(new int[]{240, 235, 230, 225, 220, 215, 210, 205, 200, 195, 190, 185, 180, 175, 170, 165, 160, 155, 150}),
    LEFT_MIDDLE_TO_UP(new int[]{145, 140, 135, 130, 125, 120, 115, 110, 105, 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50}),
    LEFT_UP_TO_MIDDLE(new int[]{55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 145, 150}),
    LEFT_MIDDLE_TO_DOWN(new int[]{150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 225, 230, 235, 240}),

    RIGHT_DOWN_TO_UP(new int[]{50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 145,
            150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 230, 235, 240, 250}),
    RIGHT_UP_TO_DOWN(new int[]{250, 240, 235, 230, 220, 215, 210, 205, 200, 195, 190, 185, 180, 175, 170, 165, 160, 155,
            150, 145, 140, 135, 130, 125, 120, 115, 110, 105, 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50}),
    RIGHT_DOWN_TO_MIDDLE(new int[]{55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 125, 130, 135, 140, 145, 150}),
    RIGHT_MIDDLE_TO_UP(new int[]{150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 225, 230, 235, 240, 250}),
    RIGHT_UP_TO_MIDDLE(new int[]{250, 240, 235, 230, 225, 220, 215, 210, 205, 200, 195, 190, 185, 180, 175, 170, 165, 160, 155, 150}),
    RIGHT_MIDDLE_TO_DOWN(new int[]{145, 140, 130, 125, 120, 115, 110, 105, 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50});

    private final int[] positions;

    ArmDirection(int[] positions) {
        this.positions = positions;
    }

    public int[] getPositions() {
        return positions;
    }
}
