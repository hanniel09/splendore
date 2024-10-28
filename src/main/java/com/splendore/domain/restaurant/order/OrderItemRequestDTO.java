package com.splendore.domain.restaurant.order;

public record OrderItemRequestDTO (
        Order order,
        String itemName,
        Integer quantity,
        Float price,
        Integer tableNumber
) {
}
