package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int order_id;
    @Column
    private String paymentMode;
    @Column
    private double amount;
    @Column
    private String status;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id",insertable = false,updatable = false)
    private Orders order;
}
