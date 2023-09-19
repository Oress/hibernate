package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "store_store_id_seq")
    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_staff_id", referencedColumnName = "staff_id", foreignKey = @ForeignKey(name = "store_manager_staff_id_fkey"))
    private Staff manager;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", foreignKey = @ForeignKey(name = "store_address_id_fkey"))
    private Address address;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;
}

