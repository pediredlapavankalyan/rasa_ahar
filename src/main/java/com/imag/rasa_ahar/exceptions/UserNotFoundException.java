package com.imag.rasa_ahar.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("User Not found in our data");
    }

}
