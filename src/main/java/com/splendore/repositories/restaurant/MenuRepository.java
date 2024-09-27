package com.splendore.repositories.restaurant;

import com.splendore.domain.restaurant.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
