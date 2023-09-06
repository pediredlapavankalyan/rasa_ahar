package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Address;

import java.util.List;

public interface AddressInterface {
    Address newAddress(Address address);
    List<Address> allAddresses(long phone);
}
