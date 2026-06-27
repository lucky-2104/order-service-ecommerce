package com.commerce.order_service.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;

@Builder
public record OrderItemResponse(
		
		UUID productId,
		String productName,
		Integer quantity,
		BigDecimal price
		
		
		) {

}
