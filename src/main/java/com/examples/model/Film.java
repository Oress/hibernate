package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "film_film_id_seq")
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "rental_duration", nullable = false)
    private Integer rentalDuration;
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

/*    @Column(name = "rating")
    private String rating;*/

    @Column(name = "special_features", columnDefinition = "TEXT[]")
    private String[] specialFeatures;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", foreignKey = @ForeignKey(name = "film_language_id_fkey"))
    private Language language;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Instant lastUpdate;

    @OneToMany(mappedBy = FilmActor_.FILM, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilmActor> filmActors;

    // It needs a custom Hibernate Type for TSVector
//    private String fulltext;

    public List<Actor> getActors() {
        return filmActors.stream().map(FilmActor::getActor).collect(Collectors.toList());
    }

    public void removeActor(Actor actor) {
        if (filmActors == null) {
            return;
        }
        filmActors.removeIf(filmActor -> filmActor.getActor().equals(actor));
    }

    public void addActor(Actor actor) {
        if (filmActors == null) {
            filmActors = new ArrayList<>();
        }
        filmActors.add(FilmActor.create(actor, this));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String[] getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String[] specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Integer getFilmId() {
        return filmId;
    }
}
