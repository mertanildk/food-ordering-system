package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    private OrderStatus status;
    private List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        setTrackingId(builder.trackingId);
        setStatus(builder.status);
        setFailureMessages(builder.failureMessages);
    }
    public static final class Builder {
        private OrderId orderId;
        private final CustomerId customerId;
        private final RestaurantId restaurantId;
        private final StreetAddress deliveryAddress;
        private final Money price;
        private final List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus status;
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

        public Builder status(OrderStatus val) {
            status = val;
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
