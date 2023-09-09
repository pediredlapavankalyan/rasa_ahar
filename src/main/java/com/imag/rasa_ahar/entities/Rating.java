package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Rating {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    private int restaurantId;
    private int userId;
    private int rating;
    //RelationShip With other entities
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurantId", referencedColumnName = "id", insertable = false, updatable = false)
    private Restaurant rated_restaurant;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User rated_user;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Restaurant getRated_restaurant() {
        return rated_restaurant;
    }

    public void setRated_restaurant(Restaurant rated_restaurant) {
        this.rated_restaurant = rated_restaurant;
    }

    public User getRated_user() {
        return rated_user;
    }

    public void setRated_user(User rated_user) {
        this.rated_user = rated_user;
    }

    //Object class Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return id == rating1.id && restaurantId == rating1.restaurantId && userId == rating1.userId && rating == rating1.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, userId, rating);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", rating=" + rating +
                '}';
    }
}
