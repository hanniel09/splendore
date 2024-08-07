package com.splendore.domain.client;

import com.splendore.domain.address.Address;
import com.splendore.domain.reservation.Reservation;
import com.splendore.domain.rooms.Rooms;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
    public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        private String name;

        private String lastName;
        private String email;
        private String phone;
        private String cpf;
        private String rg;

        @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
        private List<Reservation> reservation;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "client_rooms",
                joinColumns = @JoinColumn(name = "client_id"),
                inverseJoinColumns = @JoinColumn(name = "room_id")
        )
        private List<Rooms> rooms;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "address_id", referencedColumnName = "id")
        private Address address;
}
