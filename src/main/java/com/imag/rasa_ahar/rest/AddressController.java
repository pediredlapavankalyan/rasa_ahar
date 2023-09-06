package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Address;
import com.imag.rasa_ahar.service.Address_Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    Address_Service as;
    @PostMapping("/addAddress")
    @Operation(summary = "To add new delivery address", description = "provide address in json format")
    public Address addAddress(Address address) {
        return  as.newAddress(address);
    }

    @GetMapping("/userAddress/{phone}")
    @Operation(summary = "To get all address of a user",description = "provide user phone number")
    public List<Address> allAddress(@PathVariable("phone")long phone){
        return as.allAddresses(phone);
    }
}
