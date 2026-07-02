package com.ecommerce.order_service.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record CreateOrderRequest(
		
		
		@NotEmpty(message="Select Atleast One product to order")
		@Valid
		List<OrderItemRequest> items
		
		) {

	
}
