package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "city_city_id_seq")
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", foreignKey = @ForeignKey(name = "fk_city"))
    private Country country;
}
