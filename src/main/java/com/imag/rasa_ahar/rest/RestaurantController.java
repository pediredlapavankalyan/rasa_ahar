package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.requestDto.RestaurantDto;
import com.imag.rasa_ahar.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class RestaurantController {
    //Dependency Injection
    @Autowired
    RestaurantService restaurantService;//rs denotes Restaurant Service

    //To Register a new restaurant
    @PostMapping("/com.imag.rasa-ahar/v1/restaurant")//Url
    @Operation(summary = "To Add a new restaurant", description = "provide restaurant details")
    public RestaurantDto addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.newRestaurant(restaurantDto);
    }

    //To get all restaurants
    @GetMapping("/com.imag.rasa-ahar/v1/restaurant/Restaurants")//url
    @Operation(summary = "To get all restaurants ", description = "execute url to get all restaurants registered")
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantService.allRestaurants();
    }

    //Get restaurant by phone number
    @GetMapping("/com.imag.rasa-ahar/v1/user-admin/restaurant/{phone}")//Url
    @Operation(summary = "To get  restaurant ", description = "Enter phone number along with url")
    public RestaurantDto getRestaurants(@PathVariable("phone") String phone) {
        return restaurantService.getByPhone(phone);
    }

    //To Update phone number
    @PutMapping("/com.imag.rasa-ahar/v1/restaurant/change-Phone-Number")//Url
    @Operation(summary = "To update phone number", description = "Enter both old and new phone numbers")
    public RestaurantDto updateNumber(@RequestParam String oldNum, @RequestParam String newNum) {
        return restaurantService.updatePhone(oldNum, newNum);
    }
    @GetMapping("/com.imag.rasa-ahar/v1/user-admin/restaurant/page/{num}")
    public List<RestaurantDto> restaurantDtosByPage(@PathVariable("num")int number){
            return  restaurantService.restaurantsPage(number);

    }

    //Restaurants having 4 and 5 rated orders
    @GetMapping("/com.imag.rasa-ahar/v1/restaurant/topRestaurants")//Url
    @Operation(summary = "To get top rated restaurants", description = "execute url to get top restaurants")
    public Set<Restaurant> topRestaurants() {
        return restaurantService.topRestaurants();
    }

    //Restaurants maintaining their average rating between 4 and 5
    @GetMapping("/com.imag.rasa-ahar/v1/restaurant/toprated")//Url
    @Operation(summary = "To get top rated restaurants", description = "execute url to get top restaurants")
    public Set<Restaurant> topRatedRestaurants() {
        return restaurantService.topRatedRestaurants();
    }

    //To get Menu of a restaurant
    @GetMapping("/com.imag.rasa-ahar/v1/restaurant/dishes/{id}")//Url
    @Operation(summary = "To get Menu of the Restaurant", description = "Enter restaurant id to get the menu")
    public Set<Menu> getMenu(@PathVariable("id") int id) {
        return restaurantService.getDishes(id);
    }

    @GetMapping("/com.imag.rasa-ahar/v1/restaurant/dish/name")
    @Operation(summary = "To get the set of restaurants providing that dish",description = "Enter dish name")
    public Set<RestaurantDto> restaurants(String dishName){
        return restaurantService.restaurantsByDishName(dishName);
    }
}
