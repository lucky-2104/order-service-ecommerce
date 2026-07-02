package com.ecommerce.order_service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ecommerce.order_service.entity.Category;

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
