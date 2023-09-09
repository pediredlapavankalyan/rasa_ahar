package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class OrderDetails {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    private int orderId;
    private int dishId;
    private int quantity;
    //RelationShip With other entities
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "orderId", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dishId", referencedColumnName = "id", insertable = false, updatable = false)
    private Menu dish;

    //Getters and Setters
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu getDish() {
        return dish;
    }

    public void setDish(Menu dish) {
        this.dish = dish;
    }

    //Object class Methods
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
