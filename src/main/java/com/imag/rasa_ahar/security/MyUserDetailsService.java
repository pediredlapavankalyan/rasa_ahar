package com.imag.rasa_ahar.security;

import com.imag.rasa_ahar.entities.User;
import com.imag.rasa_ahar.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByName(username);
        if(user==null){
            throw new UsernameNotFoundException("USER NOT FOUND WITH THAT CREDENTIALS");
        }
        return new UserInfo(user);
    }
}
