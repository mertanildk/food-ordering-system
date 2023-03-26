package com.food.ordering.system.order.service.domain.dto.track;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderQuery {//client siparişin en son durumunu sorgulamak için bu classı kullanacak
    @NotNull
    private final UUID trackingId;

}
