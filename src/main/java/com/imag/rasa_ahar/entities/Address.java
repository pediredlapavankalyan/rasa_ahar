package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Address {
    //Fields
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private String state;
    private String city;
    private String street;
    private int pincode;

    //RelationShip With other entities
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "id", updatable = false, insertable = false)
    private User user;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Object class Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && userId == address.userId && pincode == address.pincode && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, state, city, street, pincode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
