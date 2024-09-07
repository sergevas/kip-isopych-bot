package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PomodoroTypeConverter implements AttributeConverter<PomodoroType, String> {
    @Override
    public String convertToDatabaseColumn(PomodoroType attribute) {
        return attribute.name();
    }

    @Override
    public PomodoroType convertToEntityAttribute(String dbData) {
        return PomodoroType.valueOf(dbData);
    }
}
