package com.imag.rasa_ahar.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Orders {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column
    private int user_id;
    @Column
    private int restaurant_id;
    @Column
    private double orderTotal;
    @Column
    private String status;
    @Column
    private int driverId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id && user_id == orders.user_id && restaurant_id == orders.restaurant_id && Double.compare(orderTotal, orders.orderTotal) == 0 && driverId == orders.driverId && Objects.equals(status, orders.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, restaurant_id, orderTotal, status, driverId);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", restaurant_id=" + restaurant_id +
                ", orderTotal=" + orderTotal +
                ", status='" + status + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}
