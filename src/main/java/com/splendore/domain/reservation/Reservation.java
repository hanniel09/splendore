package com.splendore.domain.reservation;

import com.splendore.domain.client.Client;
import com.splendore.domain.rooms.Rooms;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Rooms room;

    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    public Reservation(Client client, Rooms room, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        this.client = client;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
