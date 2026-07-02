package com.ecommerce.order_service.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ErrorResponse(
		
		String message,
		int status,
		LocalDateTime timestamp
		
		) {

}
