package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Orders {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column
    private int userId;
    @Column
    private int restaurantId;
    @Column
    private double orderTotal;
    @Column
    private String status;
    @Column
    private int driverId;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId",referencedColumnName = "id",updatable = false,insertable = false)
    private  User user_ordered;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurantId",referencedColumnName = "id",insertable = false,updatable = false)
    private Restaurant restaurant;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "driverId",referencedColumnName = "id",updatable = false,insertable = false)
    private  Drivers driver;
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderDetails> orderDetails;
    @OneToOne(mappedBy = "order")
    @JsonIgnore
    private Payment payment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public int getRestaurant_id() {
        return restaurantId;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurantId = restaurant_id;
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
        return id == orders.id && userId == orders.userId && restaurantId == orders.restaurantId && Double.compare(orderTotal, orders.orderTotal) == 0 && driverId == orders.driverId && Objects.equals(status, orders.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, restaurantId, orderTotal, status, driverId);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user_id=" + userId +
                ", restaurant_id=" + restaurantId +
                ", orderTotal=" + orderTotal +
                ", status='" + status + '\'' +
                ", driverId=" + driverId +
                '}';
    }
}
