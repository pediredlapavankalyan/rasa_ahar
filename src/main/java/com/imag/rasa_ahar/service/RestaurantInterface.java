package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.requestDto.RestaurantDto;

import java.util.List;
import java.util.Set;

public interface RestaurantInterface {
    RestaurantDto newRestaurant(RestaurantDto restaurant);

    List<RestaurantDto> allRestaurants();

    RestaurantDto getByPhone(String phone);

    RestaurantDto updatePhone(String oldNum, String newNum);

    Set<RestaurantDto> restaurantsByDishName(String dishName);
}
