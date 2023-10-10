package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.exceptions.InValidEmailFormatException;
import com.imag.rasa_ahar.exceptions.InValidMobileNumber;
import com.imag.rasa_ahar.exceptions.UserAlreadyExistsException;
import com.imag.rasa_ahar.exceptions.UserNotFoundException;
import com.imag.rasa_ahar.repo.UserRepo;
import com.imag.rasa_ahar.requestDto.UserRequest;
import com.imag.rasa_ahar.responseDto.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface {
    //Dependency Injection
    @Autowired
    UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserService() {
    }

    @Autowired
    ModelMapper modelMapper = new ModelMapper();

    public User dtoToUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    public UserResponse userToDtoResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }


    @Override
    public UserResponse newUser(UserRequest userRequest) throws UserAlreadyExistsException {
        User user = dtoToUser(userRequest);
        if (userRepo.findByPhone(user.getPhone()) != null) {
            throw new UserAlreadyExistsException();
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepo.save(user);
            return userToDtoResponse(user);
        }

    }

    @Override
    public List<UserResponse> allUsers() {
        return userRepo.findAll().stream().map(u -> modelMapper.map(u, UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponse userByPhone(String phone)throws UserNotFoundException {
        User user = userRepo.findByPhone(phone);
        String s ="a";
        Character c =s.charAt(0);

        if (user == null) {
            throw new UserNotFoundException();
        } else {
            return userToDtoResponse(user);
        }
    }

    @Override
    public Set<Order> orderHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepo.findByName(userName);
            Set<Order> orders = user.getOrders();
            if (orders.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "NO ORDERS FOUND");
            } else {
                return orders;
            }
        }
    }


