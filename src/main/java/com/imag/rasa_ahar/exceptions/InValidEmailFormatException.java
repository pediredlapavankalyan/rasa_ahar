package com.imag.rasa_ahar.exceptions;

public class InValidEmailFormatException extends RuntimeException{
    public InValidEmailFormatException(String message) {
        super(message);
    }

    public InValidEmailFormatException() {
    }
}
