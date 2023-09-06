package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.User;

import java.util.List;

public interface UserInterface {
    User newUser(User user);

    List<User> allUsers();

    User userByPhone(long phone);
}
