package com.splendore.domain.client;

import com.splendore.domain.address.AddressDTO;

import java.util.List;
import java.util.UUID;

public record ClientRequestDTO(
        String name,
        String lastName,
        String email,
        String phone,
        String cpf,
        String rg,
        List<UUID> reservationIds,
        List<UUID> roomIds,
        AddressDTO address
) {
}
