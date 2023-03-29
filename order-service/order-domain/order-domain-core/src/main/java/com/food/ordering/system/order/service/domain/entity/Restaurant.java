package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import lombok.Getter;

import java.util.List;


@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private final List<Product> products;
    private final boolean active;

    public Restaurant(RestaurantId restaurantId,List<Product> products ) {
        super.setId(restaurantId);
        this.products = products;
        this.active = true;
    }
}
