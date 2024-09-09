package dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.StringJoiner;

import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState.PAUSED;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState.STARTED;
import static dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroType.*;

@Entity
@SequenceGenerator(name = "seq_gen", sequenceName = "pomodoro_seq", allocationSize = 1)
@NamedQuery(name = "Pomodoro.findAll", query = "from Pomodoro")
public class Pomodoro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Long id;
    private Integer pomodoroDuration;
    private Integer shortBreakDuration;
    private Integer longBreakDuration;
    private Integer numOfPomodoros;
    private Integer currentNumber;
    private Integer elapsedTime;
    private PomodoroType type;
    private PomodoroState state;

    public Pomodoro() {
    }

//    public Pomodoro(Integer pomodoroDuration,
//                    Integer shortBreakDuration,
//                    Integer longBreakDuration,
//                    Integer numOfPomodoros,
//                    Integer currentNumber,
//                    Integer elapsedTime,
//                    PomodoroType type,
//                    PomodoroState state) {
//        this.pomodoroDuration = pomodoroDuration;
//        this.shortBreakDuration = shortBreakDuration;
//        this.longBreakDuration = longBreakDuration;
//        this.numOfPomodoros = numOfPomodoros;
//        this.currentNumber = currentNumber;
//        this.elapsedTime = elapsedTime;
//        this.type = type;
//        this.state = state;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPomodoroDuration() {
        return pomodoroDuration;
    }

    public void setPomodoroDuration(Integer pomodoroDuration) {
        this.pomodoroDuration = pomodoroDuration;
    }

    public Integer getShortBreakDuration() {
        return shortBreakDuration;
    }

    public void setShortBreakDuration(Integer shortBreakDuration) {
        this.shortBreakDuration = shortBreakDuration;
    }

    public Integer getLongBreakDuration() {
        return longBreakDuration;
    }

    public void setLongBreakDuration(Integer longBreakDuration) {
        this.longBreakDuration = longBreakDuration;
    }

    public Integer getNumOfPomodoros() {
        return numOfPomodoros;
    }

    public void setNumOfPomodoros(Integer numOfPomodoros) {
        this.numOfPomodoros = numOfPomodoros;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public PomodoroType getType() {
        return type;
    }

    public void setType(PomodoroType type) {
        this.type = type;
    }

    public PomodoroState getState() {
        return state;
    }

    public void setState(PomodoroState state) {
        this.state = state;
    }

    public boolean isPaused() {
        return PAUSED.equals(state);
    }

    public Pomodoro reset() {
        currentNumber = 1;
        elapsedTime = 0;
        pomodoroDuration = 0;
        shortBreakDuration = 0;
        longBreakDuration = 0;
        numOfPomodoros = 0;
        type = POMODORO;
        state = STARTED;
        return this;
    }

    public void resetElapsedTime() {
        this.elapsedTime = 0;
    }

    public Integer incElapsedTime() {
        return ++elapsedTime;
    }

    public Integer incCurrentNumber() {
        return ++currentNumber;
    }

    public boolean isPomodoroFinished() {
        return POMODORO.equals(type) && elapsedTime >= pomodoroDuration;
    }

    public boolean isShortBreakFinished() {
        return SHORT_BREAK.equals(type) && elapsedTime >= shortBreakDuration;
    }

    public boolean isLongBreakFinished() {
        return LONG_BREAK.equals(type) && elapsedTime >= longBreakDuration;
    }

    public boolean isFinished() {
        return isPomodoroFinished() || isShortBreakFinished() || isLongBreakFinished();
    }

    public boolean isLastPomodoro() {
        return 0 == currentNumber % numOfPomodoros;
    }

    public void toggleToLongBreak() {
        this.type = LONG_BREAK;
        resetElapsedTime();
    }

    public void toggleToShortBreak() {
        this.type = SHORT_BREAK;
        resetElapsedTime();
    }

    public void toggleToPomodoro() {
        this.type = POMODORO;
        resetElapsedTime();
    }

    public void toggle() {
        if (POMODORO.equals(type) && isLastPomodoro()) {
            toggleToLongBreak();
        } else if (POMODORO.equals(type) && !isLastPomodoro()) {
            toggleToShortBreak();
        } else {
            toggleToPomodoro();
            incCurrentNumber();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pomodoro that = (Pomodoro) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pomodoro.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("pomodoroDuration=" + getPomodoroDuration())
                .add("shortBreakDuration=" + getShortBreakDuration())
                .add("longBreakDuration=" + getLongBreakDuration())
                .add("numOfPomodoros=" + getNumOfPomodoros())
                .add("currentNumber=" + getCurrentNumber())
                .add("elapsedTime=" + getElapsedTime())
                .add("type=" + getType())
                .add("state=" + getState())
                .toString();
    }
}
