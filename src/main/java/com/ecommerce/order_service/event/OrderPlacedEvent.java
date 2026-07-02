package com.ecommerce.order_service.event;

import lombok.Builder;

import java.util.List;
import java.util.UUID;


@Builder
public record OrderPlacedEvent(

        UUID orderId,
        List<OrderItemEvent> items

) {
}
