package com.ecommerce.order_service.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderConfirmedNotification(

        UUID orderId,
        String userEmail,
        BigDecimal totalAmount,
        LocalDateTime confirmedAt


) {
}
