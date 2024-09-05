package dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity("pomodoro_state")
public class PomodoroState {

    public enum State {
        NEW,
        STARTED,
        PAUSED,
        STOPPED;
    }

    @Id
    private Long id;
    private Integer currentNumber;
    private State state;

    public Long getId() {
        return id;
    }

    public PomodoroState setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public PomodoroState setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
        return this;
    }

    public State getState() {
        return state;
    }

    public PomodoroState setState(State state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PomodoroState that = (PomodoroState) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PomodoroState{" +
                "id=" + id +
                ", currentNumber=" + currentNumber +
                ", state=" + state +
                '}';
    }
}
