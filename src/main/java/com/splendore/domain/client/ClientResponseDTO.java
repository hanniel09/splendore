package com.splendore.domain.client;

import com.splendore.domain.address.AddressDTO;
import com.splendore.domain.reservation.ReservationResponseDTO;
import com.splendore.domain.rooms.RoomsResponseDTO;

import java.util.List;
import java.util.UUID;

public record ClientResponseDTO(
        UUID id,
        String name,
        String lastName,
        String email,
        String phone,
        String cpf,
        String rg,
        List<ReservationResponseDTO> reservations,
        List<RoomsResponseDTO> rooms,
        AddressDTO address
) {
}
