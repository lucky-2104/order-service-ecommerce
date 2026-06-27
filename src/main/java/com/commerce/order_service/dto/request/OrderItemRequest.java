package com.commerce.order_service.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record OrderItemRequest(
		
		
		@NotNull(message="Product ID can`t be null get it id of product you want")
		UUID productId,
		
		@Positive(message="Quantity of product can`t be negative or zero")
		@NotNull(message="Quantity of product can`t be null")
		Integer quantity
		
		) {

}
