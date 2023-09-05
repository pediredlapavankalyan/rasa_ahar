package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService us;
    @PostMapping("/addUser")
    @Operation(summary = "To Add new User",description = "give user details in json format")
    public User addUser(@RequestBody User user)throws Exception
    {
        return us.newUser(user);
    }

}
