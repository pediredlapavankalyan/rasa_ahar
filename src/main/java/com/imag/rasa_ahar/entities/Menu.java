package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Menu {
    @Id
    @Column
    private  int id;
    @Column
    private int restaurant_id;
    @Column
    private String dishName;
    @Column
    private double price;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Restaurant restaurant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id && restaurant_id == menu.restaurant_id && Double.compare(price, menu.price) == 0 && Objects.equals(dishName, menu.dishName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurant_id, dishName, price);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", restaurant_id=" + restaurant_id +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                '}';
    }
}
