package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.InValidMobileNumber;

import java.util.List;
import java.util.Set;

public interface UserInterface {
    User newUser(User user) throws InValidMobileNumber;

    List<User> allUsers();

    User userByPhone(String phone);
    Set<Order> orderHistory(int id);
}
