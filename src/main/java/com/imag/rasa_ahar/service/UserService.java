package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.rest.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements UserInterface {
    @Autowired
    UserRepo ur;

    @Override
    public User newUser(User user) throws ResponseStatusException {
        if (ur.findByPhone(user.getPhone()) != null) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User already have account");
        } else
            ur.save(user);
        return user;
    }

    @Override
    public List<User> allUsers() {
        return ur.findAll();
    }

    @Override
    public User userByPhone(long phone) {
        User user = ur.findByPhone(phone);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO USER FOUND WITH THAT PHONE NUMBER");
        else
            return user;
    }

}
