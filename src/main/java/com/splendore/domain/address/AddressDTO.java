package com.splendore.domain.address;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String street,
        String city,
        String state,
        String zipCode,
        String country
) {}
