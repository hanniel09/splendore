package com.splendore.domain.rooms;

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
    private long id;

    private RoomStatus roomStatus;

    private long floorNumber;

    private long roomNumber;

    private RoomsLevel roomLevel;

    private RoomServices roomServices;

    private long bedsInRoomNumber;

}
