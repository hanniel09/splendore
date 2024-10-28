package com.splendore.domain.restaurant.menu;

import java.util.List;

public record MenuRequestDTO(
        String name,
        Float price,
        List<String> ingredients,
        String description
) {
}
