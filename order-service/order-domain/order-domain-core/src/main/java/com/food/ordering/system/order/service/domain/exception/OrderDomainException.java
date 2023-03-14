package com.food.ordering.system.order.service.domain.exception;


import com.food.ordering.system.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
    //Base exceptiondan extend ettik hata alındığında veLoglara bakıldığında
    //Bu exception alınmışsa hatanın Orderdan geldiğini anlayabiliriz.
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
