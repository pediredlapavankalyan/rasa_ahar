package com.imag.rasa_ahar.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {
    private Validation validation;
    @BeforeEach
    public void init(){
        validation=new Validation();
    }
    @DisplayName("Testing Mobile number Validation method1")
    @ParameterizedTest
    @ValueSource(strings = {"9110793168","8923459879","7036110229","9948572266"})
    public void testVerifyMobile1(String value){
            assertTrue(validation.verifyMobile(value),"Failed even giving correct values");
    }
    @DisplayName("Testing Mobile number Validation method2")
    @ParameterizedTest
    @ValueSource(strings = {"911079316","","703611","9948572 124","2wffafa"})
    public void testVerifyMobile2(String value){
        assertFalse(validation.verifyMobile(value),"It accepting wrong mobile number");
    }

}