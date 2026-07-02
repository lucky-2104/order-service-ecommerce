package com.ecommerce.order_service.event;

import com.ecommerce.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusEventConsumer {

    private final OrderService orderService;

    @KafkaListener(
            topics = "order-status",
            groupId="order-service-group"
            )
    public void consume(OrderStatusEvent event){
        orderService.updatedStatus(event);
    }
}
