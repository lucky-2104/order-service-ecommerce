package com.ecommerce.order_service.messaging;


import com.ecommerce.order_service.config.RabbitMQConfig;
import com.ecommerce.order_service.dto.request.OrderConfirmedNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishOrderConfirmed(OrderConfirmedNotification notification){

        log.info("INFO: ----- ORDER SERVICE --------");
        log.info("INFO:  Order Confirmed for Order ID : {}",notification.orderId());
        log.info("INFO: Publishing ORDER CONFIRMED EVENT: {}",notification.orderId());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                notification

        );
    }

}
