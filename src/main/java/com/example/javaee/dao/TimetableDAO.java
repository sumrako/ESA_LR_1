package com.example.javaee.dao;

import com.example.javaee.data.Timetable;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class TimetableDAO {
    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    public void save(Timetable timetable) {
        em.persist(timetable);
    }
    public Timetable getById (Long id) {
        return em.find(Timetable.class, id);
    }

    public List<Timetable> getByPerformanceName (String performanceName) {
        return em.createQuery("SELECT f from Timetable f WHERE :name= f.performance.name", Timetable.class)
                .setParameter("name", performanceName)
                .getResultList();
    }

    public List<Timetable> getAll() {
        return em.createQuery("SELECT b from Timetable b", Timetable.class)
                .getResultList();
    }

    public void update(Timetable timetable) {
        em.merge(timetable);
    }

    public void deleteById(Long id) {
        Timetable timetable = em.find(Timetable.class, id);
        em.remove(timetable);
    }
}
