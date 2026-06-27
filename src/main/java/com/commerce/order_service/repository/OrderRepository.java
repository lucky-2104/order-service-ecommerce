package com.commerce.order_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commerce.order_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order,UUID>{
	
	List<Order> findByUserEmail(String email);
}
