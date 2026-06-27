package com.commerce.order_service.response;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ErrorResponse(
		
		String message,
		int status,
		LocalDateTime timestamp
		
		) {

}
