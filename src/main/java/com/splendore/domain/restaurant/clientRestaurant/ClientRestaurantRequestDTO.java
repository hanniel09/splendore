package com.splendore.domain.restaurant.clientRestaurant;

import com.splendore.domain.restaurant.clientRestaurant.enums.CustomerLocationConsumed;
import com.splendore.domain.restaurant.clientRestaurant.enums.ReservationStatus;

import java.time.LocalDateTime;

public record ClientRestaurantRequestDTO(
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
