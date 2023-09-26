package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.responseDto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    //derived Query
    //Phone is field in User Entity
    User findByPhone(String phone);
    User findByName(String name);
//    public List<UserResponse> allUsers();
}
