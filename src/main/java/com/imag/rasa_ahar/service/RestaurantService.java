package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.repo.MenuRepo;
import com.imag.rasa_ahar.repo.RatingRepo;
import com.imag.rasa_ahar.repo.RestaurantRepo;
import com.imag.rasa_ahar.requestDto.RestaurantRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantService implements RestaurantInterface {

    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    RatingRepo ratingRepo;
    @Autowired
    MenuRepo menuRepo;
    @Autowired
    ModelMapper modelMapper;

    public Restaurant dtoToRestaurant(RestaurantRequest restaurantRequest){
        return modelMapper.map(restaurantRequest,Restaurant.class);
    }
    public RestaurantRequest restaurantToDto(Restaurant restaurant){
        return modelMapper.map(restaurant,RestaurantRequest.class);
    }
    public List<RestaurantRequest> restaurantListToDto(List<Restaurant> restaurants){
        return restaurants.stream().map(r->modelMapper.map(r,RestaurantRequest.class)).collect(Collectors.toList());
    }



    @Override
    public RestaurantRequest newRestaurant(RestaurantRequest restaurantRequest) throws ResponseStatusException {
        if (restaurantRepo.findByPhone(restaurantRequest.getPhone()) != null)
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Restaurant already registered");
        else {
            Restaurant restaurant = dtoToRestaurant(restaurantRequest);
            restaurantRepo.save(restaurant);
            return restaurantToDto(restaurant);
        }
    }

    @Override
    public List<RestaurantRequest> allRestaurants() {
        return restaurantListToDto(restaurantRepo.findAll());
    }

    @Override
    public RestaurantRequest getByPhone(long phone) {
        return restaurantToDto(restaurantRepo.findByPhone(phone));
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

    public Set<RestaurantRequest> restaurantsByDishName(String dishName){
        Set<Restaurant> restaurants= new LinkedHashSet<>();
        List<Menu> restaurantIds = menuRepo.findByDishNameContainingIgnoreCase(dishName);
        for(Menu restaurantId:restaurantIds){
            Optional<Restaurant> restaurant = restaurantRepo.findById(restaurantId.getRestaurantId());
            if(restaurant.isPresent()){
                restaurants.add(restaurant.get());
            }
        }
        return  restaurants.stream().map(r->modelMapper.map(r,RestaurantRequest.class)).collect(Collectors.toSet());
    }
}
