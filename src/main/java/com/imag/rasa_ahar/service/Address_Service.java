package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Address;
import com.imag.rasa_ahar.repo.AddressRepo;
import com.imag.rasa_ahar.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Address_Service implements AddressInterface {
    @Autowired
    AddressRepo ar;

    @Autowired
    UserRepo ur;

    @Override
    public Address newAddress(Address address) {
        ar.save(address);
        return address;
    }

    @Override
    public List<Address> allAddresses(long phone) {
        return ar.findAllByUserId(ur.findByPhone(phone).getId());
    }

}
