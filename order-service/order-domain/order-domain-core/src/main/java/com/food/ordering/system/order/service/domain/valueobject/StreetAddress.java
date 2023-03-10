package com.food.ordering.system.order.service.domain.valueobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.UUID;

@Data
@Getter
@EqualsAndHashCode(callSuper = false)
public class StreetAddress {
    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;
}
