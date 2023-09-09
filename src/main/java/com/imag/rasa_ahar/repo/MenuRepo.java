package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MenuRepo extends JpaRepository<Menu,Integer> {
    @Query("Select restaurantId from Menu where dishName like %:dishName% ")
    Set<Integer> restaurantsByDish(@Param("dishName")String dishName);

    List<Menu> findByDishNameContainingIgnoreCase(String dishName);
}
