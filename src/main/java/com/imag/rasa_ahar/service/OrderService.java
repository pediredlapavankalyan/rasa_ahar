package com.imag.rasa_ahar.service;

import com.imag.rasa_ahar.entities.Menu;
import com.imag.rasa_ahar.entities.Order;
import com.imag.rasa_ahar.entities.OrderDetails;
import com.imag.rasa_ahar.entities.Restaurant;
import com.imag.rasa_ahar.exceptions.CanNotOrderFromDiffrentRestaurants;
import com.imag.rasa_ahar.repo.MenuRepo;
import com.imag.rasa_ahar.repo.OrderDetailsRepo;
import com.imag.rasa_ahar.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    //Dependency Injection
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    MenuRepo menuRepo;

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
    public Order placeOrder(int userId, List<OrderDetails> orderDetails){
        Order order = new Order();
        order.setUserId(userId);
        Restaurant restaurant ;
        double total=0;
        Optional<Menu> dish = menuRepo.findById(orderDetails.get(0).getDishId());
        if (dish.isPresent()){
            restaurant = dish.get().getRestaurant();
            order.setRestaurantId(restaurant.getId());
            for(OrderDetails item:orderDetails){
                Menu menu = menuRepo.findById(item.getDishId()).get();
                if(restaurant.equals(menu.getRestaurant())){
                    total+=menu.getPrice()*item.getQuantity();
                }
                else {
                    throw new CanNotOrderFromDiffrentRestaurants();
                }
            }
            order.setOrderTotal(total);
            order.setStatus("Pending");
            order.setDriverId((int)(Math.random()*10));
            order.setTime(LocalDateTime.now());
            orderRepo.save(order);
            for(OrderDetails item:orderDetails){
                item.setOrderId(order.getId());
                orderDetailsRepo.save(item);
            }
        }
        return order;
    }
}
