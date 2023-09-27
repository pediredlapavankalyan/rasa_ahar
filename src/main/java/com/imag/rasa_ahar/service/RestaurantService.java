package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.repo.MenuRepo;
import com.imag.rasa_ahar.repo.RatingRepo;
import com.imag.rasa_ahar.repo.RestaurantRepo;
import com.imag.rasa_ahar.requestDto.RestaurantDto;
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

    public RestaurantService() {
    }

    public RestaurantService(RestaurantRepo restaurantRepo,ModelMapper modelMapper) {
        this.restaurantRepo = restaurantRepo;
        this.modelMapper=modelMapper;
    }

    public Restaurant dtoToRestaurant(RestaurantDto restaurantDto){
        return modelMapper.map(restaurantDto,Restaurant.class);
    }
    public RestaurantDto restaurantToDto(Restaurant restaurant){
        return modelMapper.map(restaurant, RestaurantDto.class);
    }
    public List<RestaurantDto> restaurantListToDto(List<Restaurant> restaurants){
        return restaurants.stream().map(r->modelMapper.map(r, RestaurantDto.class)).collect(Collectors.toList());
    }



    @Override
    public RestaurantDto newRestaurant(RestaurantDto restaurantDto) throws ResponseStatusException {
        if (restaurantRepo.findByPhone(restaurantDto.getPhone()) != null)
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Restaurant already registered");
        else {
            Restaurant restaurant = dtoToRestaurant(restaurantDto);
            restaurantRepo.save(restaurant);
            return restaurantToDto(restaurant);
        }
    }

    @Override
    public List<RestaurantDto> allRestaurants() {
        return restaurantListToDto(restaurantRepo.findAll());
    }

    @Override
    public RestaurantDto getByPhone(String phone) {
        return restaurantToDto(restaurantRepo.findByPhone(phone));
    }

    @Override
    public String updatePhone(String oldNum, String newNum) {
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

    public Set<RestaurantDto> restaurantsByDishName(String dishName){
        Set<Restaurant> restaurants= new LinkedHashSet<>();
        List<Menu> restaurantIds = menuRepo.findByDishNameContainingIgnoreCase(dishName);
        for(Menu restaurantId:restaurantIds){
            Optional<Restaurant> restaurant = restaurantRepo.findById(restaurantId.getRestaurantId());
            if(restaurant.isPresent()){
                restaurants.add(restaurant.get());
            }
        }
        return  restaurants.stream().map(r->modelMapper.map(r, RestaurantDto.class)).collect(Collectors.toSet());
    }
}
