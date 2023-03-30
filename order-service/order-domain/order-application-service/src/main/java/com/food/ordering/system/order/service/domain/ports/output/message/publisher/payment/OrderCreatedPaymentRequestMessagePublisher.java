package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

/**
 *This interface is used to publish OrderCreatedEvent to payment service
 * Bu interface, OrderCreatedEvent'i payment servisine  yaymak için kullanılır
 */
public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}

