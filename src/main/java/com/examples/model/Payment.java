package com.examples.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "payment_payment_id_seq")
    @Column(name = "payment_id", nullable = false)
    private Integer paymentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", foreignKey = @ForeignKey(name = "payment_customer_id_fkey"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", foreignKey = @ForeignKey(name = "payment_staff_id_fkey"))
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rental_id", referencedColumnName = "rental_id", foreignKey = @ForeignKey(name = "payment_rental_id_fkey"))
    private Rental rental;

    @Column(name = "amount", nullable = false, precision = 5, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private Instant paymentDate;
}
