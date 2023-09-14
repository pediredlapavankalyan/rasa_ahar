package com.imag.rasa_ahar.exceptions;

public class InValidMobileNumber extends RuntimeException{
    private String message;

    public InValidMobileNumber(){}
    public InValidMobileNumber(String message) {
        this.message=message;
    }
}
