package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class RestaurantController {
    @Autowired
    RestaurantService rs;

    @PostMapping("/addRestaurant")
    @Operation(summary = "To Add a new restaurant", description = "provide restaurant details")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return rs.newRestaurant(restaurant);
    }

    @GetMapping("/getRestaurants")
    @Operation(summary = "To get all restaurants ",description = "execute url to get all restaurants registered")
    public List<Restaurant> getAllRestaurants(){
        return rs.allRestaurants();
    }
    @GetMapping("/getRestaurants/{phone}")
    @Operation(summary = "To get  restaurant ",description = "Enter phone number along with url")
    public Restaurant getRestaurants(@PathVariable("phone")long phone){
        return rs.getByPhone(phone);
    }

    @PutMapping("/updatePhoneNumber")
    @Operation(summary = "To update phone number",description = "Enter both old and new phone numbers")
    public String updateNumber(@RequestParam long oldNum,@RequestParam long newNum){
        return rs.updatePhone(oldNum, newNum);
    }

    @GetMapping("/restaurant_dishes/{id}")
    @Operation(summary = "To get Menu of the Restaurant",description = "Enter restaurant id to get the menu")
    public List<String> getMenu(@PathVariable("id")int id){
        return  rs.getDishes(id);
    }
}
