package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.InValidEmailFormatException;
import com.imag.rasa_ahar.exceptions.InValidMobileNumber;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserInterface {
    //Dependency Injection
    @Autowired
    UserRepo userRepo;
    @Autowired
    Validation validation;


    @Override
    public User newUser(User user) throws InValidMobileNumber {
        if (userRepo.findByPhone(user.getPhone()) != null) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User already have account");
        } else {
            //validation
            if (validation.verifyMobile(user.getPhone())) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);
                if (validation.verifyEmail(user.getEmail())) {
                    userRepo.save(user);
                    return user;
                } else {
                    throw new InValidEmailFormatException("Enter a Valid email ");
                }
            } else {
                throw new InValidMobileNumber();
            }
        }

    }

    @Override
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @Override
    public User userByPhone(String phone) {
        User user = userRepo.findByPhone(phone);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO USER FOUND WITH THAT PHONE NUMBER");
        } else {
            return user;
        }
    }

    @Override
    public Set<Order> orderHistory(int id) {
        var user = userRepo.findById(id);
        if (user.isPresent()) {
            Set<Order> orders = user.get().getOrders();
            if (orders.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "NO ORDERS FOUND");
            } else {
                return orders;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

}
