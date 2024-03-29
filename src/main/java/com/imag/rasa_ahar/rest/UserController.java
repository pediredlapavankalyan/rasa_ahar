package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.InValidMobileNumber;
import com.imag.rasa_ahar.exceptions.UserAlreadyExistsException;
import com.imag.rasa_ahar.exceptions.UserNotFoundException;
import com.imag.rasa_ahar.requestDto.AuthenticateRequest;
import com.imag.rasa_ahar.requestDto.UserRequest;
import com.imag.rasa_ahar.responseDto.UserResponse;
import com.imag.rasa_ahar.service.JwtService;
import com.imag.rasa_ahar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    //Dependency Injection
    @Autowired
    UserService userService;
    @Autowired
    UserResponse userResponse;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/message")
    public String getMessage(){
        return "Successful";
    }

    //To Register new user
    @PostMapping("/com.imag.rasa-ahar/v1/user/new-user")//Url
    @Operation(summary = "To Add new User", description = "give user details in json format")
    public UserResponse addUser(@RequestBody UserRequest user) throws UserAlreadyExistsException {
        try {
            return userService.newUser(user);
        } catch (InValidMobileNumber e) {
            throw new RuntimeException(e.toString());
        }
    }

    //To Get Users List
    @GetMapping("/com.imag.rasa-ahar/v1/user-admin/users-list")//Url
    //@PreAuthorize("hasAuthority('admin')")
    @Operation(summary = "To get list of users ", description = "run the url to get list of users")
    public List<UserResponse> getUsers() {
        return userService.allUsers();
    }

    //To Get Order history of an user
    @GetMapping("/com.imag.rasa-ahar/v1/user/order/order-history")//Url
    @Operation(summary = "To get Order history", description = "Enter user id to get order history")
    public Set<Order> getOrderHistory() {
        return userService.orderHistory();
    }

    //To search the user using their phone number
    @GetMapping("/com.imag.rasa-ahar/v1/user-admin/{phone}")//Url
    public UserResponse getUser(@PathVariable("phone") String phone) throws UserNotFoundException {
        return userService.userByPhone(phone);
    }

    //JWT
    @PostMapping("/com.imag.rasa-ahar/v1/user/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthenticateRequest authenticateRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authenticateRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

}
