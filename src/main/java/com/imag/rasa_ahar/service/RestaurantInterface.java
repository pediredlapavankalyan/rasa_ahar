package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.requestDto.RestaurantRequest;

import java.util.List;
import java.util.Set;

public interface RestaurantInterface {
    RestaurantRequest newRestaurant(RestaurantRequest restaurant);

    List<RestaurantRequest> allRestaurants();

    RestaurantRequest getByPhone(long phone);

    String updatePhone(long oldNum, long newNum);

    Set<RestaurantRequest> restaurantsByDishName(String dishName);
}
