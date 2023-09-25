package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.InValidMobileNumber;
import com.imag.rasa_ahar.requestDto.UserRequest;
import com.imag.rasa_ahar.responseDto.UserResponse;

import java.util.List;
import java.util.Set;

public interface UserInterface {
    UserResponse newUser(UserRequest user) throws InValidMobileNumber;

    List<UserResponse> allUsers();

    UserResponse userByPhone(String phone);
    Set<Order> orderHistory(int id);
}
