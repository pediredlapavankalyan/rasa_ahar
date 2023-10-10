package com.imag.rasa_ahar.service.junit;

import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.UserAlreadyExistsException;
import com.imag.rasa_ahar.exceptions.UserNotFoundException;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.requestDto.UserRequest;
import com.imag.rasa_ahar.responseDto.UserResponse;
import com.imag.rasa_ahar.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSeriveJUnitTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Test
    @Disabled
    public void  testNewUser(){
        UserRequest userRequest = new UserRequest("Bharath","bharat@gmail.com","mypassword","8072342323","user");
        UserResponse userResponse = new UserResponse("Bharath","bharat@gmail.com","8072342323");
        assertAll(
                ()->assertEquals(userResponse,userService.newUser(userRequest))
        );
    }
    @Test
    public void testAllUsers(){
        assertTrue(()->userService.allUsers() instanceof List<UserResponse>,()->"Error in Listing all users");
    }
    @Test
    public void testUserByPhone(){
        UserResponse userResponse = new UserResponse("Bharath","bharat@gmail.com","8072342323");
       assertAll(
               ()->  assertThrows(UserNotFoundException.class,()->userService.userByPhone("9923423433")),
               ()->  assertEquals(userResponse,userService.userByPhone("8072342323"))
       );
    }
    @Test
    public void testOrderHistory(){
        assertTrue(()->userService.orderHistory() instanceof Set<Order>,()->"Error in finding order history of an user");
    }
}
