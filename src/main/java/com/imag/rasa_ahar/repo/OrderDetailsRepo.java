package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {
    //Derived Query
    //OrderId is a field(Property) in Order Details Entity
    Set<OrderDetails> findByOrderId(int orderId);
}
