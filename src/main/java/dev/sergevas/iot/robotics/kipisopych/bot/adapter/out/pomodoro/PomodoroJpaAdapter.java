package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroWriter;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class PomodoroJpaAdapter implements PomodoroReader, PomodoroWriter {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Optional<Pomodoro> read() {
        var pomodoros = em.createNamedQuery("Pomodoro.findAll", Pomodoro.class).getResultList();
        return pomodoros.isEmpty() ? Optional.empty() : Optional.of(pomodoros.get(0));
    }

    @Override
    @Transactional
    public void write(Pomodoro pomodoro) {
        em.persist(pomodoro);
    }
}
