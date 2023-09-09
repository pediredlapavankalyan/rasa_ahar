package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Address;
import com.imag.rasa_ahar.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    //Dependency Injection
    @Autowired
    AddressService addressService;//as denotes Address_Service

    //To add a new Address of User
    @PostMapping("/user/Address")//url
    @Operation(summary = "To add new delivery address", description = "provide address in json format")
    public Address addAddress(Address address) {
        return addressService.newAddress(address);
    }

    //To find address of an user
    @GetMapping("/user/userAddress/{phone}")//url
    @Operation(summary = "To get all address of a user", description = "provide user phone number")
    public List<Address> allAddress(@PathVariable("phone") long phone) {
        return addressService.allAddresses(phone);
    }
}
