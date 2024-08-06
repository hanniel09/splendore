package com.splendore.domain.rooms;

import com.splendore.domain.clients.Client;
import com.splendore.domain.rooms.enums.RoomsServices;
import com.splendore.domain.rooms.enums.RoomsStatus;
import com.splendore.domain.rooms.enums.RoomsLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Unique identifier the room", example = "1")
    private Long id;

    @Schema(description = "Current status of the room", example = "AVAILABLE", allowableValues = {"AVAILABLE", "OCCUPIED", "RESERVED"})
    private RoomsStatus roomsStatus;

    @Schema(description = "Floor number where the room is located", example = "2")
    private Long floorNumber;

    @Schema(description = "Room number", example="205")
    private Long roomsNumber;

    @Schema(description = "Level of the room", example = "GOLD", allowableValues = {"GOLD", "PLATINUM", "DIAMOND", "MASTER", "PRESIDENTIAL"})
    private RoomsLevel roomsLevel;

    @Schema(description = "Services available in the room", example = "BREAKFAST", allowableValues = {"BREAKFAST", "LUNCH", "GYM", "SPA", "ALL"})
    private RoomsServices roomsServices;

    @Schema(description = "Number of beds in the room", example = "2")
    private Long bedsInRoomNumber;

    @ManyToMany(mappedBy = "rooms")
    private List<Client> clients = new ArrayList<>();

    public Rooms(RoomsRequestDTO data) {
        this.roomsStatus = data.roomStatus();
        this.floorNumber = data.floorNumber();
        this.roomsNumber = data.roomsNumber();
        this.roomsLevel = data.roomsLevel();
        this.roomsServices = data.roomsServices();
        this.bedsInRoomNumber = data.bedsInRoomNumber();
    }

    public Rooms(long id, RoomsStatus roomsStatus, long floorNumber, long roomsNumber, RoomsLevel roomsLevel, RoomsServices roomsServices, long bedsInRoomNumber) {
        this.id = id;
        this.roomsStatus = roomsStatus;
        this.floorNumber = floorNumber;
        this.roomsNumber = roomsNumber;
        this.roomsLevel = roomsLevel;
        this.roomsServices = roomsServices;
        this.bedsInRoomNumber = bedsInRoomNumber;
    }
}
