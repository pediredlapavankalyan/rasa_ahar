package com.imag.rasa_ahar.responseDto;

import com.imag.rasa_ahar.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponse {
    private int id;
    private String name;
    private String email;
    private String phone;


    public UserResponse convert(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }
    public List<UserResponse> convert(List<User> users){
        return users.stream().map(this::convert).collect(Collectors.toList());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
