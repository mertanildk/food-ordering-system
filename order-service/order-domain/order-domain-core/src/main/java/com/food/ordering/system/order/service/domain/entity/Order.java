package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class Order extends AggregateRoot<OrderId> {//Id OrderId olarak ayarlanacak

    //final : Order oluşturma esnasında dolu olması gereken alanlar (final)
    //private : İş mantığına göre değişebilir alanlar (private)
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        setTrackingId(builder.trackingId);
        setOrderStatus(builder.orderStatus);
        setFailureMessages(builder.failureMessages);
    }


    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        long itemId = 1;
        for (OrderItem orderItem : items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
        }
    }


    public void pay() {
        if (!orderStatus.equals(OrderStatus.PENDING))
            throw new OrderDomainException("Order is not in correct state for pay operation");
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (!orderStatus.equals(OrderStatus.PAID))
            throw new OrderDomainException("Order is not in correct state for approve operation");
        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (!orderStatus.equals(OrderStatus.PAID))
            throw new OrderDomainException("Order is not in correct state for initCancel operation");
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null)
            failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());

        if (this.failureMessages == null)
            this.failureMessages = failureMessages;
    }

    public void cancel(List<String> failureMessages) {
        if (!(orderStatus.equals(OrderStatus.PENDING) || orderStatus.equals(OrderStatus.CANCELLING)))
            throw new OrderDomainException("Order is not in correct state for cancel operation");
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }


    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();

    }

    private void validateItemsPrice() {
        Money ordersItemTotal =
                items.stream().map(orderItem -> {
                    validateItemPrice(orderItem);
                    return orderItem.getSubTotal();
                }).reduce(Money.ZERO, Money::add);
        if (!price.equals(ordersItemTotal)) {
            throw new OrderDomainException("Total price: " + price.getAmount()
                    + " is not equals to Order items total:" + ordersItemTotal.getAmount());
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount()
                    + " is not valid for product " + orderItem.getProduct().getId().getValue());
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total amount must be greater than Zero");
        }
    }

    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in correct state for initialization!");
        }
    }

    public static final class Builder {
        private OrderId orderId;
        private final CustomerId customerId;
        private final RestaurantId restaurantId;
        private final StreetAddress deliveryAddress;
        private final Money price;
        private final List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        public Builder(CustomerId customerId, RestaurantId restaurantId, StreetAddress deliveryAddress, Money price, List<OrderItem> items) {
            this.customerId = customerId;
            this.restaurantId = restaurantId;
            this.deliveryAddress = deliveryAddress;
            this.price = price;
            this.items = items;
        }

        public Builder id(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
