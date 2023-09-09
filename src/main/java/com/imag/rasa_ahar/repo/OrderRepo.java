package com.imag.rasa_ahar.repo;

import com.imag.rasa_ahar.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
