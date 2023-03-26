package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;


/**
 * This class is used to save order to database
 * bu sınıf order'ı veritabanına kaydetmek için kullanılır.
 */
public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByTrackingId(TrackingId trackingId);//Optinal<Order> return because order may not exist.

}
