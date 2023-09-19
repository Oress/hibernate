package com.examples.application;

import com.examples.model.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SampleService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Film someBusinessOperation(Integer filmId) {
        return em.find(Film.class, filmId);
    }
}
