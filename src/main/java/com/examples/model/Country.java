package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "country_country_id_seq")
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "country", nullable = false, length = 50)
    private String country;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;
}
