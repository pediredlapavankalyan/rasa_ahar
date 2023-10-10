package com.imag.rasa_ahar.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
    @NotNull(message = "User Name Should not be null")
    private String name;
    @Email(message = "Invalid Email")
    private String email;
    private String password;
    @Pattern(regexp = "^\\d{10}$",message = "Invalid Mobile Number")
    private String phone;
    @NotNull
    private  String role;

    public UserRequest(String name, String email, String password, String phone, String role)  {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
