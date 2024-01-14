package com.example.javaee.dao;

import com.example.javaee.data.Performance;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class PerformanceDAO {

    @PersistenceContext(name = "my-persistence-unit")
    private EntityManager em;

    public void save(Performance performance) {
        em.persist(performance);
    }
    public Performance getById (Long id) {
        return em.find(Performance.class, id);
    }

    public Performance getByName (String name) {
        return em.createQuery("SELECT f from Performance f WHERE :name = f.name", Performance.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Performance> getAll() {
        return em.createQuery("SELECT f from Performance f", Performance.class)
                .getResultList();
    }

    public List<String> getAllNames() {
        return em.createQuery("SELECT b.name from Performance b", String.class)
                .getResultList();
    }

    public void update(Performance performance) {
        em.merge(performance);
    }

    public void deleteById(Long id) {
        Performance performance = em.find(Performance.class, id);
        em.remove(performance);
    }
}
