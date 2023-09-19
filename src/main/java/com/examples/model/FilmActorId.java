package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Embeddable
public class FilmActorId {
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "actor_id")
    private Integer actorId;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
}
