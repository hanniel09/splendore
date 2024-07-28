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

    private long roomNumber;

    private RoomsLevel roomsLevel;

    private RoomsServices roomsServices;

    private long bedsInRoomNumber;
}
