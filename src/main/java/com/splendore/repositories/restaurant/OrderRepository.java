package com.splendore.repositories.restaurant;

import com.splendore.domain.restaurant.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
