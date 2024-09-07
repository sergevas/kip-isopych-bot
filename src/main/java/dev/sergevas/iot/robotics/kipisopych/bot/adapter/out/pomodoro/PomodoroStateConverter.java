package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PomodoroStateConverter implements AttributeConverter<PomodoroState, String> {
    @Override
    public String convertToDatabaseColumn(PomodoroState attribute) {
        return attribute.name();
    }

    @Override
    public PomodoroState convertToEntityAttribute(String dbData) {
        return PomodoroState.valueOf(dbData);
    }
}
