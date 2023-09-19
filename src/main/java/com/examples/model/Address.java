package com.examples.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_address_id_seq")
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Column(name = "address2", length = 50)
    private String address2;
    @Column(name = "district", nullable = false, length = 20)
    private String district;
    @Column(name = "postal_code", length = 10)
    private String postalCode;
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Instant lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", foreignKey = @ForeignKey(name = "fk_address_city"))
    private City city;
}
