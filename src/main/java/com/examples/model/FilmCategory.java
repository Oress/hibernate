package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import java.time.Instant;

@Entity
@Table(name = "film_category")
public class FilmCategory {
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name="film.filmId", column = @Column(name="film_id")),
        @AttributeOverride(name="category.categoryId", column = @Column(name="category_id"))
    })
    private FilmCategoryId id;

/*    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", foreignKey = @ForeignKey(name = "film_category_film_id_fkey"))
    @NaturalId
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", foreignKey = @ForeignKey(name = "film_category_category_id_fkey"))
    @NaturalId
    private Category category;*/

    @CreationTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;
}
