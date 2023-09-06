package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Orders;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RestaurantService implements RestaurantInterface {

    @Autowired
    RestaurantRepo rr;

    @Override
    public Restaurant newRestaurant(Restaurant restaurant) throws ResponseStatusException {
        if (rr.findByPhone(restaurant.getPhone()) != null)
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Restaurant already registered");
        else {
            rr.save(restaurant);
            return restaurant;
        }
    }

    @Override
    public List<Restaurant> allRestaurants() {
        return rr.findAll();
    }

    @Override
    public Restaurant getByPhone(long phone) {
        return rr.findByPhone(phone);
    }

    @Override
    public String updatePhone(long oldNum, long newNum) {
        Restaurant r = rr.findByPhone(oldNum);
        if (r != null) {
            r.setPhone(newNum);
            rr.save(r);
            return r.toString();
        } else {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Incorrect Old Phone NUmber");
        }
    }

    public List<String> getDishes(int id) throws ResponseStatusException {
        var res = rr.findById(id);
        if (res.isPresent()) {
            var menu= res.get().getMenuItems();
            List<String> items=new ArrayList<>();
            for(var item:menu){
                items.add(item.getDishName()+" : "+item.getPrice());
            }
            return items;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can Not found restaurant with that id");
        }
    }
}
