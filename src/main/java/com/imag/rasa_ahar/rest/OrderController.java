package com.imag.rasa_ahar.rest;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.OrderDetails;
import com.imag.rasa_ahar.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class OrderController {
    //Dependency Injection
    @Autowired
    OrderService orderService;//os denotes OrderService

    //To get The Order Status
    @GetMapping("/com.imag.rasa-ahar/v1/user/order/orderStatus")//url
    @Operation(summary = "To get order Status", description = "provide order id to get the status")
    public String orderStatus(@RequestParam int orderId) {
        return orderService.status(orderId);
    }

    //To Get complete details of order
    @GetMapping("/com.imag.rasa-ahar/v1/user/order/orderDetails/{id}")//Url
    @Operation(summary = "To get order details", description = "Enter Order id to get the details of order")
    public Set<Menu> details(@PathVariable("id") int orderId) {
        return orderService.orderDetails(orderId);
    }

    @PostMapping("/com.imag.rasa-ahar/v1/user/order")
    @Operation(summary = "To place a new order",description = "Provide dish id and quantity ")
    public Order newOrder(@RequestBody List<OrderDetails> orderDetails){
        return orderService.placeOrder( orderDetails);
    }
}
