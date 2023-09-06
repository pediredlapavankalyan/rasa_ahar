package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService us;
    @PostMapping("/addUser")
    @Operation(summary = "To Add new User",description = "give user details in json format")
    public User addUser(@RequestBody User user)throws ResponseStatusException
    {
        return us.newUser(user);
    }

    @GetMapping("getUsers")
    @Operation(summary = "To get list of users ",description = "run the url to get list of users")
    public List<User> getUsers()
    {
        return us.allUsers();
    }

    @GetMapping("/getByPhone/{phone}")
    public  User getUser(@PathVariable("phone") long phone){
        return  us.userByPhone(phone);
    }

}
