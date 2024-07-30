package com.splendore.domain.rooms;

import com.splendore.domain.rooms.enums.RoomsServices;
import com.splendore.domain.rooms.enums.RoomsStatus;
import com.splendore.domain.rooms.enums.RoomsLevel;

public record RoomsRequestDTO(RoomsStatus roomStatus, long floorNumber, long roomsNumber, RoomsLevel roomsLevel, RoomsServices roomsServices, long bedsInRoomNumber) {
}
