package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Address;

import java.util.List;

public interface AddressInterface {

    //To add new Address of an user
    Address newAddress(Address address);

    //To get all address of an user
    List<Address> allAddresses(long phone);
}
