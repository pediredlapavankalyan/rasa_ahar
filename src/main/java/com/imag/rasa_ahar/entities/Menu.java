package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Menu {
    //Fields
    @Id
    @Column
    private int id;

    private int restaurantId;
    private String dishName;
    private double price;

    //RelationShip With other entities
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurantId", referencedColumnName = "id", insertable = false, updatable = false)
    private Restaurant restaurant;

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

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    //Object class Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id && restaurantId == menu.restaurantId && Double.compare(price, menu.price) == 0 && Objects.equals(dishName, menu.dishName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, dishName, price);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant_id=" + restaurantId +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                '}';
    }
}
