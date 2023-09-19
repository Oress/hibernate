package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rental_rental_id_seq")
    @Column(name = "rental_id", nullable = false)
    private Integer rentalId;

    @Column(name = "rental_date", nullable = false)
    private Instant rentalDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", foreignKey = @ForeignKey(name = "rental_inventory_id_fkey"))
    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", foreignKey = @ForeignKey(name = "rental_customer_id_fkey"))
    private Customer customer;

    @Column(name = "return_date")
    private Instant returnDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", foreignKey = @ForeignKey(name = "rental_staff_id_key"))
    private Staff staff;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;
}
