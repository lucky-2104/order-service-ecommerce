package com.ecommerce.order_service.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderItemEvent(
        UUID productId,
        Integer quantity
) {
}
