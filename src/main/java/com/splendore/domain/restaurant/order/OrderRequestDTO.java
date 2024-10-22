package com.splendore.domain.restaurant.order;

import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurant;
import com.splendore.domain.restaurant.order.enums.OrderStatus;
import com.splendore.domain.restaurant.tables.Tables;

import java.util.List;

public record OrderRequestDTO(
        ClientRestaurant client,
        Tables table,
        Float totalPrice,
        OrderStatus status,
        List<OrderItem> orderItems
) {
}
