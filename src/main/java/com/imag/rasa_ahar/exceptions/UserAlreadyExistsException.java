package com.imag.rasa_ahar.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("User with the provided information already exists.");
    }
}
