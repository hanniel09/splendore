package com.splendore.repositories.restaurant;

import com.splendore.domain.restaurant.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
