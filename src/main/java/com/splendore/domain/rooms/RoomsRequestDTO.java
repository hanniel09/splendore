package com.splendore.domain.rooms;

import com.splendore.domain.rooms.enums.RoomsServices;
import com.splendore.domain.rooms.enums.RoomsStatus;
import com.splendore.domain.rooms.enums.RoomsLevel;
import io.swagger.v3.oas.annotations.media.Schema;

public record RoomsRequestDTO(
        @Schema(description = "Current status of the room", example = "AVAILABLE", allowableValues = {"AVAILABLE", "OCCUPIED", "RESERVED", "CLEANING_UP", "IN_MAINTENANCE", "CLOSE"})
        RoomsStatus roomStatus,

        @Schema(description = "Floor number where the room is located", example = "2")
        Long floorNumber,

        @Schema(description = "Room number", example = "205")
        Long roomsNumber,

        @Schema(description = "Level of the room", example = "GOLD", allowableValues = {"GOLD", "PLATINUM", "DIAMOND", "MASTER", "PRESIDENTIAL"})
        RoomsLevel roomsLevel,

        @Schema(description = "Services available in the room", example = "BREAKFAST", allowableValues = {"BREAKFAST", "LUNCH", "GYM", "SPA", "ALL"})
        RoomsServices roomsServices,

        @Schema(description = "Number of beds in the room", example = "2")
        Long bedsInRoomNumber
        ) {
}
