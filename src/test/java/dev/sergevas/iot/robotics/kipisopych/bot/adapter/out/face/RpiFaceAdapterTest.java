package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.face;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RpiFaceAdapterTest {

    @Test
    void displayBCD() {
        var bitChars0 = Integer.toBinaryString(0).toCharArray();
        assertArrayEquals(new char[]{'0'}, bitChars0);
        var bitChars7 = Integer.toBinaryString(7).toCharArray();
        assertArrayEquals(new char[]{'1', '1', '1'}, bitChars7);
        var bitChars8 = Integer.toBinaryString(8).toCharArray();
        assertArrayEquals(new char[]{'1', '0', '0', '0'}, bitChars8);
    }
}
