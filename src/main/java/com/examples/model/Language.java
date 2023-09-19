package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "language_language_id_seq")
    @Column(name = "language_id", nullable = false)
    private Integer languageId;
    @Column(name = "name", nullable = false)
    private String name;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private String lastUpdate;
}
