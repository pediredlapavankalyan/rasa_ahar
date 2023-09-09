package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
    //Derived Query
    //Phone is field in Restaurant Entity
    Restaurant findByPhone(long phone);


}
