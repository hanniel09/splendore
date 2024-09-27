package com.splendore.repositories.restaurant;

import com.splendore.domain.restaurant.tables.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablesRepository extends JpaRepository<Tables, Long> {
}
