package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Address;
import com.imag.rasa_ahar.repo.AddressRepo;
import com.imag.rasa_ahar.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressInterface {
    //Dependency Injection
    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserRepo userRepo;

    //To add new Address of an user
    @Override
    public Address newAddress(Address address) {
        addressRepo.save(address);
        return address;
    }

    //To get all address of an user
    @Override
    public List<Address> allAddresses(String phone) {
        return addressRepo.findAllByUserId(userRepo.findByPhone(phone).getId());
    }

}
