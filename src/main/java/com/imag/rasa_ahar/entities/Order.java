package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    //Fields
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int restaurantId;
    private double orderTotal;
    private String status;
    private int driverId;

    //RelationShip With other entities
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "id", updatable = false, insertable = false)
    private User user_ordered;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurantId", referencedColumnName = "id", insertable = false, updatable = false)
    private Restaurant restaurant;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "driverId", referencedColumnName = "id", updatable = false, insertable = false)
    private Driver driver;
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderDetails> orderDetails;
    @OneToOne(mappedBy = "order")
    @JsonIgnore
    private Payment payment;

    //Getters and Setters
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public User getUser_ordered() {
        return user_ordered;
    }

    public void setUser_ordered(User user_ordered) {
        this.user_ordered = user_ordered;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    //Object class Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userId == order.userId && restaurantId == order.restaurantId && Double.compare(orderTotal, order.orderTotal) == 0 && driverId == order.driverId && Objects.equals(status, order.status);
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
