package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.YesNoConverter;

import java.time.Instant;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_customer_id_seq")
    @Column(name = "customer_id")
    private Integer customerId;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Store store;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", foreignKey = @ForeignKey(name = "customer_address_id_fkey"))
    private Address address;

    @Column(name = "activebool", nullable = false)
    private Boolean activeBool = Boolean.TRUE;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private Instant createDate;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @Column(name = "active", nullable = false)
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean active;
}
