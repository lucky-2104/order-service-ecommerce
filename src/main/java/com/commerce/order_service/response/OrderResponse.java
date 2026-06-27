package com.commerce.order_service.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.commerce.order_service.entity.OrderStatus;

import lombok.Builder;

@Builder
public record OrderResponse(
		
		
		UUID id,
		OrderStatus status,
		BigDecimal totalAmount,
		List<OrderItemResponse> items,
		LocalDateTime createdAt
		) {
	


}
