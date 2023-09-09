package com.imag.rasa_ahar.entities;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class Validation {

    public boolean verifyMobile(long number){
       return Pattern.matches("^\\d{10}$",number+"");
    }
}
