package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;

/**
 *This interface is used to publish OrderCancelledEvent to payment service
 * Bu interface, OrderCancelledEvent'i payment servisine  yaymak için kullanılır
 */
public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
