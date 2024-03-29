package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address,Integer> {
    //Derived Query
    //UserId is Field in Address Entity
    List<Address> findAllByUserId(int userId);
}
