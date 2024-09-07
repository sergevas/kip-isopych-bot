package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.pomodoro;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroReader;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.pomodoro.PomodoroWriter;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.Pomodoro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PomodoroJpaAdapter implements PomodoroReader, PomodoroWriter {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Pomodoro read() {
        return em.createNamedQuery("Pomodoro.findAll", Pomodoro.class).getSingleResult();
    }

    @Override
    @Transactional
    public void write(Pomodoro pomodoro) {
        em.persist(pomodoro);
    }
}
