package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "inventory_inventory_id_seq")
    @Column(name = "inventory_id", nullable = false)
    private Integer inventoryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", foreignKey = @ForeignKey(name = "inventory_film_id_fkey"))
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Store store;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;
}
