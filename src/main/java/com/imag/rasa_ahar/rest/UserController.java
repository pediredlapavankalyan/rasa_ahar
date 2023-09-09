package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.InValidMobileNumber;
import com.imag.rasa_ahar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    //Dependency Injection
    @Autowired
    UserService userService;

    //To Register new user
    @PostMapping("/User/newuser")//Url
    @Operation(summary = "To Add new User", description = "give user details in json format")
    public User addUser(@RequestBody User user) {
        try {
            return userService.newUser(user);
        } catch (InValidMobileNumber e) {
            throw new RuntimeException(e.toString());
        }
    }

    //To Get Users List
    @GetMapping("/Users")//Url
    @Operation(summary = "To get list of users ", description = "run the url to get list of users")
    public List<User> getUsers() {
        return userService.allUsers();
    }

    //To Get Order history of an user
    @GetMapping("/user/order/orderHistory")//Url
    @Operation(summary = "To get Order history", description = "Enter user id to get order history")
    public Set<Order> getOrderHistory(@RequestParam int id) {
        return userService.orderHistory(id);
    }

    //To search the user using their phone number
    @GetMapping("/user/Phone/{phone}")//Url
    public User getUser(@PathVariable("phone") long phone) {
        return userService.userByPhone(phone);
    }

}
