package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.order.service.domain.entity.Order;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public abstract class OrderEvent implements DomainEvent<Order> {
    public final Order order;
    public final ZonedDateTime zonedDateTime;
}
