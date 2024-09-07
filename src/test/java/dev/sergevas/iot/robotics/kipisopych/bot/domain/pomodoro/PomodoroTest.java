package dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro;

import org.junit.jupiter.api.Test;

import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState.PAUSED;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState.STARTED;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroType.*;
import static org.junit.jupiter.api.Assertions.*;

class PomodoroTest {

    @Test
    void testNew() {
        var pomodoro = new Pomodoro();
        assertEquals(1, pomodoro.getCurrentNumber());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(POMODORO, pomodoro.getType());
        assertEquals(STARTED, pomodoro.getState());
    }

    @Test
    void isPaused() {
        var pomodoro = new Pomodoro();
        assertFalse(pomodoro.isPaused());
        pomodoro.setState(PAUSED);
        assertTrue(pomodoro.isPaused());
    }

    @Test
    void incElapsedTime() {
        var pomodoro = new Pomodoro();
        pomodoro.setElapsedTime(7);
        pomodoro.incElapsedTime();
        assertEquals(8, pomodoro.getElapsedTime());
    }

    @Test
    void incCurrentNumber() {
        var pomodoro = new Pomodoro();
        pomodoro.setCurrentNumber(3);
        pomodoro.incCurrentNumber();
        assertEquals(4, pomodoro.getCurrentNumber());
    }

    @Test
    void isPomodoroFinished() {
        var pomodoro = new Pomodoro();
        pomodoro.setPomodoroDuration(25);
        pomodoro.setShortBreakDuration(5);
        pomodoro.setLongBreakDuration(15);
        pomodoro.setElapsedTime(24);
        assertFalse(pomodoro.isPomodoroFinished());
        pomodoro.setElapsedTime(25);
        assertTrue(pomodoro.isPomodoroFinished());
        pomodoro.setElapsedTime(26);
        assertTrue(pomodoro.isPomodoroFinished());
        pomodoro.setType(SHORT_BREAK);
        assertFalse(pomodoro.isPomodoroFinished());
    }

    @Test
    void isShortBreakFinished() {
        var pomodoro = new Pomodoro();
        pomodoro.setType(SHORT_BREAK);
        pomodoro.setShortBreakDuration(5);
        pomodoro.setElapsedTime(4);
        assertFalse(pomodoro.isShortBreakFinished());
        pomodoro.setElapsedTime(5);
        assertTrue(pomodoro.isShortBreakFinished());
        pomodoro.setElapsedTime(6);
        assertTrue(pomodoro.isShortBreakFinished());
        pomodoro.setType(POMODORO);
        assertFalse(pomodoro.isShortBreakFinished());
    }

    @Test
    void isLongBreakFinished() {
        var pomodoro = new Pomodoro();
        pomodoro.setType(LONG_BREAK);
        pomodoro.setLongBreakDuration(15);
        pomodoro.setElapsedTime(14);
        assertFalse(pomodoro.isLongBreakFinished());
        pomodoro.setElapsedTime(15);
        assertTrue(pomodoro.isLongBreakFinished());
        pomodoro.setElapsedTime(16);
        assertTrue(pomodoro.isLongBreakFinished());
        pomodoro.setType(POMODORO);
        assertFalse(pomodoro.isLongBreakFinished());
    }

    @Test
    void isFinished() {
        var pomodoro = new Pomodoro();
        pomodoro.setPomodoroDuration(25);
        assertFalse(pomodoro.isFinished());
        pomodoro.setElapsedTime(25);
        assertTrue(pomodoro.isFinished());
    }

    @Test
    void isLastPomodoro() {
        var pomodoro = new Pomodoro();
        pomodoro.setNumOfPomodoros(4);
        assertFalse(pomodoro.isLastPomodoro());
        pomodoro.incCurrentNumber();
        assertFalse(pomodoro.isLastPomodoro());
        pomodoro.incCurrentNumber();
        assertFalse(pomodoro.isLastPomodoro());
        pomodoro.incCurrentNumber();
        assertTrue(pomodoro.isLastPomodoro());
    }

    @Test
    void toggleToLongBreak() {
        var pomodoro = new Pomodoro();
        pomodoro.toggleToLongBreak();
        assertEquals(LONG_BREAK, pomodoro.getType());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(0, pomodoro.getCurrentNumber());
    }

    @Test
    void toggleToShortBreak() {
        var pomodoro = new Pomodoro();
        pomodoro.toggleToShortBreak();
        assertEquals(SHORT_BREAK, pomodoro.getType());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(0, pomodoro.getCurrentNumber());
    }

    @Test
    void toggleToPomodoro() {
        var pomodoro = new Pomodoro();
        pomodoro.toggleToPomodoro();
        assertEquals(POMODORO, pomodoro.getType());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(1, pomodoro.getCurrentNumber());
    }

    @Test
    void should_toggle_toLongBreak() {
        var pomodoro = new Pomodoro();
        pomodoro.setNumOfPomodoros(4);
        pomodoro.setCurrentNumber(4); // The last Pomodoro
        pomodoro.toggle();
        assertEquals(LONG_BREAK, pomodoro.getType());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(0, pomodoro.getCurrentNumber());
    }

    @Test
    void should_toggle_toShortBreak() {
        var pomodoro = new Pomodoro();
        pomodoro.setNumOfPomodoros(4);
        pomodoro.setCurrentNumber(3); // Not the last Pomodoro
        pomodoro.toggle();
        assertEquals(SHORT_BREAK, pomodoro.getType());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(0, pomodoro.getCurrentNumber());

    }

    @Test
    void should_toggle_toPomodoro_fromShortBreak() {
        var pomodoro = new Pomodoro();
        pomodoro.setType(SHORT_BREAK);
        pomodoro.setNumOfPomodoros(0);
        pomodoro.setCurrentNumber(0);
        pomodoro.toggle();
        assertEquals(POMODORO, pomodoro.getType());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(1, pomodoro.getCurrentNumber());
    }

    @Test
    void should_toggle_toPomodoro_fromLongBreak() {
        var pomodoro = new Pomodoro();
        pomodoro.setType(LONG_BREAK);
        pomodoro.setNumOfPomodoros(0);
        pomodoro.setCurrentNumber(0);
        pomodoro.toggle();
        assertEquals(POMODORO, pomodoro.getType());
        assertEquals(0, pomodoro.getElapsedTime());
        assertEquals(1, pomodoro.getCurrentNumber());
    }
}