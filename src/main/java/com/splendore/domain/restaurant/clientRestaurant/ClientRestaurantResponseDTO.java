package com.splendore.domain.restaurant.clientRestaurant;

import com.splendore.domain.restaurant.clientRestaurant.enums.CustomerLocationConsumed;
import com.splendore.domain.restaurant.clientRestaurant.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClientRestaurantResponseDTO(
        UUID id,
        String name,
        String lastName,
        Integer tableNumber,
        Float price,
        Integer quantity,
        String email,
        String phone,
        LocalDateTime reservationDateTime,
        ReservationStatus status,
        String specialRequest,
        Float totalOrderPrice,
        CustomerLocationConsumed customerLocationConsumed,
        Boolean hasOrderOpen
) { }
