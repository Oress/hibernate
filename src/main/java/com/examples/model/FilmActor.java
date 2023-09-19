package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
// https://stackoverflow.com/questions/63999885/how-to-use-namedentitygraph-with-embeddedid
@Entity
@Table(name = "film_actor")
public class FilmActor {
    @EmbeddedId
    private FilmActorId id;

    @MapsId("filmId")
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", foreignKey = @ForeignKey(name = "film_actor_film_id_fkey"))
    private Film film;

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "actor_id", referencedColumnName = "actor_id", foreignKey = @ForeignKey(name = "film_actor_actor_id_fkey"))
    private Actor actor;

    @CreationTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    public static FilmActor create(Actor actor, Film film) {
        FilmActor filmActor = new FilmActor();
        filmActor.id = new FilmActorId();
        filmActor.id.setFilmId(film.getFilmId());
        filmActor.id.setActorId(actor.getActorId());
        filmActor.actor = actor;
        filmActor.film = film;
        return filmActor;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
