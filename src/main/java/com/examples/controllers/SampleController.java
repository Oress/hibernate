package com.examples.controllers;

import com.examples.application.SampleService;
import com.examples.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.LazyInitializationException;
import org.hibernate.jpa.SpecHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

@RestController
public class SampleController {
    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private EntityManager emWired;

    @Autowired
    private SampleService sampleService;

    @GetMapping("/wo-transactional")
    public void standartApproachWithoutTransactional() {
        // we have to inject EntityManagerFactory because we would get exception otherwise
        // (I guess it's because of the spring proxy over EntityManager)
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Film film = em.find(Film.class, 1);
            List<Actor> actors = film.getActors();
            actors.forEach(actor -> System.out.println(actor.getFirstName()));

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }


    @GetMapping("/create-new-film")
    @Transactional
    public void withTransactionalAnnotation() {
        EntityManager em = emWired;

        Random r = new Random();
        Language lang = em.getReference(Language.class, r.nextInt(1, 7));

        List<Integer> ints = new ArrayList<>(IntStream.range(1, 201).boxed().toList());
        Collections.shuffle(ints);
        ints = ints.subList(0, 10);

        List<Actor> actors = ints.stream().map(id -> em.getReference(Actor.class, id)).toList();

        // Transient State
        Film newFilm = new Film();
        long l = System.currentTimeMillis();
        newFilm.setTitle("New Film" + l);
        newFilm.setDescription("New Film Description" + l);
        newFilm.setRentalDuration(3);
        newFilm.setLanguage(lang);
        newFilm.setLength(120);
        newFilm.setReleaseYear(r.nextInt(1900, 2021));
        newFilm.setRentalRate(BigDecimal.valueOf(r.nextFloat(10, 40) ));
        newFilm.setReplacementCost(BigDecimal.valueOf(r.nextFloat(10, 40)));

        // the cascade annotation is important because hibernate would not persist film_actor entities in the DB otherwise
        actors.forEach(newFilm::addActor);

        em.persist(newFilm);
    }

    @GetMapping("/remove-random-actor")
    @Transactional
    public void removeRandomActor(@RequestParam Integer filmId) {
        EntityManager em = emWired;

        // This example demonstrates usage of orphanRemoval=true
        Film film = em.find(Film.class, filmId);
        List<Actor> actors = film.getActors();
        System.out.println("Before removal size: " + actors.size());
        Actor actorToRemove = actors.get(0);
        film.removeActor(actorToRemove);
        System.out.println("After removal size: " + film.getActors().size());
    }

    @GetMapping("/non-fetchable-proxy")
    public void nonFetchableProxy(@RequestParam Integer filmId) {
        EntityManager em = emf.createEntityManager();
        Film film = em.find(Film.class, 1);
        em.close();

        try {
            // Will fail to fetch actors because the session is closed
            film.getActors().forEach(actor -> System.out.println(actor.getFirstName()));
        } catch (LazyInitializationException e) {
            System.out.println("This is expected due to the fact that @Transactional annotation implicitly closes the session");
        }
    }

    @GetMapping("/unfetched-proxy")
    public void unfetchedProxies(@RequestParam Integer filmId) {
        EntityManager em = emWired;
        Film film = em.find(Film.class, 1);
        // this way there is no additional roundtrips to the DB
        film.getActors().forEach(actor -> System.out.println(actor.getActorId()));
    }

    @GetMapping("/entity-graph")
    public void entityGraph(@RequestParam Integer filmId) {
        var graph = emWired.createEntityGraph(Film.class);

        // this way we left join the linking table film_actor and the actor table.
        graph.addSubgraph(Film_.filmActors).addSubgraph(FilmActor_.ACTOR);
        Film film = emWired.find(Film.class, 1, Map.of(SpecHints.HINT_SPEC_FETCH_GRAPH, graph));
        film.getActors().forEach(actor -> System.out.println(actor.getFirstName()));
    }
}
