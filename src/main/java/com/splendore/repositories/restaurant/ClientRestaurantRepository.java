package com.splendore.repositories.restaurant;

import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRestaurantRepository extends JpaRepository<ClientRestaurant, UUID> {
}
