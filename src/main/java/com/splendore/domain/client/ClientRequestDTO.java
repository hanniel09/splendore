package com.splendore.domain.client;

import com.splendore.domain.address.AddressDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record ClientRequestDTO(
        String name,
        String lastName,
        String email,
        String phone,
        String cpf,
        String rg,
        AddressDTO address,
        List<UUID> reservationIds,
        List<Long> roomIds
) {
    public ClientRequestDTO {
        if (roomIds == null) {
            roomIds = Collections.emptyList();
        }
        if (reservationIds == null) {
            reservationIds = Collections.emptyList();
        }
    }
}
