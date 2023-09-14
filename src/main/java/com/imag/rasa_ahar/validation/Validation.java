package com.imag.rasa_ahar.validation;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validation {

    public boolean verifyMobile(String number){
       return Pattern.matches("^\\d{10}$",number+"");
    }
}