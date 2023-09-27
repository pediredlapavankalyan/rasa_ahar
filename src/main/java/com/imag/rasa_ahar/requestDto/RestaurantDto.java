package com.imag.rasa_ahar.requestDto;

import java.util.Objects;

public class RestaurantDto {
    private String name;
    private String address;
    private String phone;
    private String city;
    private String state;
    private int pinCode;

    public RestaurantDto() {
    }

    public RestaurantDto(String name, String address, String phone, String city, String state, int pinCode) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto that = (RestaurantDto) o;
        return pinCode == that.pinCode && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(city, that.city) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phone, city, state, pinCode);
    }
}
