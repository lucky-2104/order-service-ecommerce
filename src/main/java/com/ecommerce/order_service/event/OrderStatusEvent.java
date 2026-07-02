package com.ecommerce.order_service.event;

import java.util.UUID;

public record OrderStatusEvent(
        UUID orderId,
        String reason
) {
}

