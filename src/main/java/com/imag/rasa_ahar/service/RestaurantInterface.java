package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Restaurant;

import java.util.List;
import java.util.Set;

public interface RestaurantInterface {
    Restaurant newRestaurant(Restaurant restaurant);

    List<Restaurant> allRestaurants();

    Restaurant getByPhone(long phone);

    String updatePhone(long oldNum, long newNum);

    Set<Restaurant> restaurantsByDishName(String dishName);
}