package com.splendore.domain.rooms;

import com.splendore.domain.rooms.enums.RoomsLevel;
import com.splendore.domain.rooms.enums.RoomsServices;
import com.splendore.domain.rooms.enums.RoomsStatus;

public record RoomsResponseDTO(
        Long id,
        RoomsStatus roomStatus,
        Long floorNumber,
        Long roomNumber,
        RoomsLevel roomsLevel,
        RoomsServices roomsServices,
        Long bedsInRoomNumber) {
}
