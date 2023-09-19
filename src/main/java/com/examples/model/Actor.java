package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "actor_actor_id_seq")
    @Column(name = "actor_id")
    private Integer actorId;
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @UpdateTimestamp
    @Column(name = "last_update")
    private Instant lastUpdate;


    public Integer getActorId() {
        return actorId;
    }

    public String getFirstName() {
        return firstName;
    }
}
