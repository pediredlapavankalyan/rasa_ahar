package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
    Restaurant findByPhone(long phone);
}
