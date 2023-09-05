package com.imag.rasa_ahar.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class OrderDetails {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int dishId;
    @Column
    private int quantity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return id == that.id && dishId == that.dishId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId, quantity);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", quantity=" + quantity +
                '}';
    }
}
