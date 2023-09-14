package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Address;

import java.util.List;

public interface AddressInterface {

    //To add new Address of a user
    Address newAddress(Address address);

    //To get all address of a user
    List<Address> allAddresses(String phone);
}
