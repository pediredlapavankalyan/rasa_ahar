package com.imag.rasa_ahar.service;

import java.util.Set;

public interface OrderInterface {
    //To get the status of an order using orderId field
    String status(int id);

    Set<String> orderDetails(int orderId);
}
