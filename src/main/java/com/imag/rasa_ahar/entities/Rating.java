package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int restaurantId;
    @Column
    private int userId;
    @Column
    private  int rating;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurantId",referencedColumnName = "id",insertable = false,updatable = false)
    private Restaurant rated_restaurant;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId",referencedColumnName = "id",insertable = false,updatable = false)
    private User rated_user;

}
