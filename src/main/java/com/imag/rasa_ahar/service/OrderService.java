package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.OrderDetails;
import com.imag.rasa_ahar.repo.OrderDetailsRepo;
import com.imag.rasa_ahar.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    //Dependency Injection
    @Autowired
    OrderRepo orderRepo;

    //To get the status of an order using orderId field
    public String status(int id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            return order.get().getStatus();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO ORDER FOUND WITH THAT ID");
        }
    }

    //To get Complete order details of an order
    //like what are the dishes included in
    public Set<Menu> orderDetails(int orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        if (order.isPresent()) {
            Set<OrderDetails> items = order.get().getOrderDetails();
            Set<Menu> details = new LinkedHashSet<>();
            for (OrderDetails item : items) {
                details.add(item.getDish());
            }
            return details;
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "NO ORDER FOUND WITH THAT ORDER ID");

        }
    }
}
