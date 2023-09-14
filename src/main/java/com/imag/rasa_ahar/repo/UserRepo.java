package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    //derived Query
    //Phone is field in User Entity
    User findByPhone(String phone);
    User findByName(String name);
}
