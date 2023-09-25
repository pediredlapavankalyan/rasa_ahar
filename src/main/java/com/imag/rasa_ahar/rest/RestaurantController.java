package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.requestDto.RestaurantRequest;
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
    @PostMapping("/Restaurant")//Url
    @Operation(summary = "To Add a new restaurant", description = "provide restaurant details")
    public RestaurantRequest addRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.newRestaurant(restaurantRequest);
    }

    //To get all restaurants
    @GetMapping("/Restaurants")//url
    @Operation(summary = "To get all restaurants ", description = "execute url to get all restaurants registered")
    public List<RestaurantRequest> getAllRestaurants() {
        return restaurantService.allRestaurants();
    }

    //Get restaurant by phone number
    @GetMapping("/restaurant/{phone}")//Url
    @Operation(summary = "To get  restaurant ", description = "Enter phone number along with url")
    public RestaurantRequest getRestaurants(@PathVariable("phone") long phone) {
        return restaurantService.getByPhone(phone);
    }

    //To Update phone number
    @PutMapping("/restaurant/PhoneNumber")//Url
    @Operation(summary = "To update phone number", description = "Enter both old and new phone numbers")
    public String updateNumber(@RequestParam long oldNum, @RequestParam long newNum) {
        return restaurantService.updatePhone(oldNum, newNum);
    }

    //Restaurants having 4 and 5 rated orders
    @GetMapping("/restaurant/topRestaurants")//Url
    @Operation(summary = "To get top rated restaurants", description = "execute url to get top restaurants")
    public Set<Restaurant> topRestaurants() {
        return restaurantService.topRestaurants();
    }

    //Restaurants maintaining their average rating between 4 and 5
    @GetMapping("/restaurant/toprated")//Url
    @Operation(summary = "To get top rated restaurants", description = "execute url to get top restaurants")
    public Set<Restaurant> topRatedRestaurants() {
        return restaurantService.topRatedRestaurants();
    }

    //To get Menu of a restaurant
    @GetMapping("/restaurant/dishes/{id}")//Url
    @Operation(summary = "To get Menu of the Restaurant", description = "Enter restaurant id to get the menu")
    public Set<Menu> getMenu(@PathVariable("id") int id) {
        return restaurantService.getDishes(id);
    }

    @GetMapping("/restaurant/dish/name")
    @Operation(summary = "To get the set of restaurants providing that dish",description = "Enter dish name")
    public Set<RestaurantRequest> restaurants(String dishName){
        return restaurantService.restaurantsByDishName(dishName);
    }
}
