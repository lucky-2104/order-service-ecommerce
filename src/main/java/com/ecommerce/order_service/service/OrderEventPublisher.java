package com.ecommerce.order_service.service;


import com.ecommerce.order_service.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

    private static final String TOPIC = "order-placed";
    private final KafkaTemplate<String , OrderPlacedEvent> kafkaTemplate;

    public void publish(OrderPlacedEvent event)
    {
        kafkaTemplate.send(
                TOPIC,
                event.orderId().toString(),
                event
        );
    }
}
