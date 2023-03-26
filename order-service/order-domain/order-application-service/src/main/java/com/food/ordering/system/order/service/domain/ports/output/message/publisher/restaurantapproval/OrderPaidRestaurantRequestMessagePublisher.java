package com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;


/**
 *This interface is used to publish OrderPaidEvent to restaurant approval service
 * Bu interface, OrderPaidEvent'i restaurant approval servisine  yaymak için kullanılır
 */
public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
