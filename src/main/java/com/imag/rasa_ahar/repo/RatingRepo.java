package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.Rating;
import com.imag.rasa_ahar.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface RatingRepo extends JpaRepository<Rating, Integer> {
    //Finder Query
    //JPQL
    @Query("Select restaurantId from Rating where rating between 4 and 5")
    List<Integer> topRatedRestaurants();
    //Finder Query
    //JPQL
    @Query("SELECT distinct restaurantId from Rating WHERE restaurantId IN(select restaurantId from Rating GROUP BY restaurantId HAVING AVG(rating) BETWEEN 4 AND 5) ")
    List<Integer> topRestaurants();

}
