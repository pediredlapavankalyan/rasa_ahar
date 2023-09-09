package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Drivers")
public class Driver {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    private String name;
    private long phone;
    private String email;
    private String location;
    //RelationShip With other entities
    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    private Set<Order> orders;
    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    //Object class Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id && phone == driver.phone && Objects.equals(name, driver.name) && Objects.equals(email, driver.email) && Objects.equals(location, driver.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, location);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
