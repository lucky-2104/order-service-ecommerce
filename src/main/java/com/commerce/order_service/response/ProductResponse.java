package com.commerce.order_service.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.commerce.order_service.entity.Category;

import lombok.Builder;

@Builder
public record ProductResponse(
		
		UUID id,
		String name,
		String description,
		BigDecimal price,
		int stockQuantity,
		Category category,
		boolean isActive,
		LocalDateTime updatedAt
		) {

}
