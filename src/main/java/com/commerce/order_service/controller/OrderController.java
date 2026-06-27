package com.commerce.order_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.order_service.dto.request.CreateOrderRequest;
 
import com.commerce.order_service.response.OrderResponse;
import com.commerce.order_service.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	
	private final OrderService orderService;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request) {
		
		OrderResponse response = orderService.createOrder(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
	@GetMapping("/myorders")
	public ResponseEntity<List<OrderResponse>> getMyOrders(){
		List<OrderResponse> response = orderService.getMyOrder();
		return ResponseEntity.ok(response);
	} 	
	
	@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id){
		
		OrderResponse response = orderService.getOrderById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrders(){
		List<OrderResponse> response = orderService.getAllOrder();
		
		return ResponseEntity.ok(response);
	}
}
