package com.splendore.domain.rooms;

import com.splendore.domain.rooms.enums.RoomsServices;
import com.splendore.domain.rooms.enums.RoomsStatus;
import com.splendore.domain.rooms.enums.RoomsLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private RoomsStatus roomsStatus;

    private long floorNumber;

    private long roomsNumber;

    private RoomsLevel roomsLevel;

    private RoomsServices roomsServices;

    private long bedsInRoomNumber;

    public Rooms(RoomsRequestDTO data) {
        this.roomsStatus = data.roomStatus();
        this.floorNumber = data.floorNumber();
        this.roomsNumber = data.roomsNumber();
        this.roomsLevel = data.roomsLevel();
        this.roomsServices = data.roomsServices();
        this.bedsInRoomNumber = data.bedsInRoomNumber();
    }

}
