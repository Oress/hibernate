package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "staff_staff_id_seq")
    @Column(name = "staff_id", nullable = false)
    Integer staffId;

    @Column(name = "first_name", nullable = false, length = 45)
    String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    String lastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", foreignKey = @ForeignKey(name = "staff_address_id_fkey"))
    Address address;

    @Column(name = "email", nullable = false, length = 50)
    String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    Store store;

    @Column(name = "active", nullable = false)
    Boolean active = Boolean.TRUE;

    @Column(name = "username", nullable = false, length = 16)
    String username;

    @Column(name = "password", nullable = false, length = 40)
    String password;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    Instant lastUpdate;
}
