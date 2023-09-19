package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "category_category_id_seq")
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Instant lastUpdate;
}
