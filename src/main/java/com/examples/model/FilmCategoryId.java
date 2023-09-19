package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Embeddable
public class FilmCategoryId {
/*    private Integer filmId;
    private Integer categoryId;*/

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", foreignKey = @ForeignKey(name = "film_category_film_id_fkey"))
    @NaturalId
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", foreignKey = @ForeignKey(name = "film_category_category_id_fkey"))
    @NaturalId
    private Category category;
}
