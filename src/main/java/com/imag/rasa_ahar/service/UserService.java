package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.rest.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
@Component
public class UserService {
    @Autowired
    UserRepo ur;
    public User newUser(User user)throws Exception{
        if(ur.findById(user.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User already have account");
        }
        else
        {
            ur.save(user);
            return user;
        }

    }

}
