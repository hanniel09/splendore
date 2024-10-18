package com.splendore.domain.restaurant.tables;

import com.splendore.domain.restaurant.order.Order;

import java.util.List;

public record TablesRequestDTO(
        Integer tableNumber,
        Integer capacity,
        Boolean isOccupied,
        List<Order> orders
) {
}
