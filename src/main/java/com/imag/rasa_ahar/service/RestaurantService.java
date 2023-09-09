package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.repo.MenuRepo;
import com.imag.rasa_ahar.repo.RatingRepo;
import com.imag.rasa_ahar.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class RestaurantService implements RestaurantInterface {

    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    RatingRepo ratingRepo;
    @Autowired
    MenuRepo menuRepo;

    @Override
    public Restaurant newRestaurant(Restaurant restaurant) throws ResponseStatusException {
        if (restaurantRepo.findByPhone(restaurant.getPhone()) != null)
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Restaurant already registered");
        else {
            restaurantRepo.save(restaurant);
            return restaurant;
        }
    }

    @Override
    public List<Restaurant> allRestaurants() {
        return restaurantRepo.findAll();
    }

    @Override
    public Restaurant getByPhone(long phone) {
        return restaurantRepo.findByPhone(phone);
    }

    @Override
    public String updatePhone(long oldNum, long newNum) {
        Restaurant restaurant = restaurantRepo.findByPhone(oldNum);
        if (restaurant != null) {
            restaurant.setPhone(newNum);
            restaurantRepo.save(restaurant);
            return restaurant.toString();
        } else {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Incorrect Old Phone NUmber");
        }
    }

    public Set<Restaurant> topRestaurants() {
        List<Integer> restaurantIds = ratingRepo.topRestaurants();
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        for (Integer id : restaurantIds) {
            Optional<Restaurant> restaurant = restaurantRepo.findById(id);
            if (restaurant.isPresent()) {
                restaurants.add(restaurantRepo.findById(id).get());
            }
        }
        return restaurants;
    }

    public Set<Restaurant> topRatedRestaurants() {
        List<Integer> restaurantIds = ratingRepo.topRatedRestaurants();
        Set<Restaurant> restaurants = new LinkedHashSet<>();
        for (Integer id : restaurantIds) {
            Optional<Restaurant> restaurant = restaurantRepo.findById(id);
            if (restaurant.isPresent()) {
                restaurants.add(restaurantRepo.findById(id).get());
            }
        }
        return restaurants;
    }

    public Set<Menu> getDishes(int id) throws ResponseStatusException {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if (restaurant.isPresent()) {
            return restaurant.get().getMenuItems();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can Not found restaurant with that id");
        }
    }

    public Set<Restaurant> restaurantsByDishName(String dishName){
        Set<Restaurant> restaurants= new LinkedHashSet<>();
        List<Menu> restaurantIds = menuRepo.findByDishNameContainingIgnoreCase(dishName);
        for(Menu restaurantId:restaurantIds){
            Optional<Restaurant> restaurant = restaurantRepo.findById(restaurantId.getRestaurantId());
            if(restaurant.isPresent()){
                restaurants.add(restaurant.get());
            }
        }
        return  restaurants;
    }
}
