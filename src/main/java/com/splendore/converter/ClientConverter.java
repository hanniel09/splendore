package com.splendore.converter;

import com.splendore.domain.address.Address;
import com.splendore.domain.address.AddressDTO;
import com.splendore.domain.client.Client;
import com.splendore.domain.client.ClientResponseDTO;
import com.splendore.domain.reservation.Reservation;
import com.splendore.domain.reservation.ReservationResponseDTO;
import com.splendore.domain.rooms.Rooms;
import com.splendore.domain.rooms.RoomsResponseDTO;

import java.util.stream.Collectors;

public class ClientConverter {
    public static ClientResponseDTO toClientResponseDTO(Client client){
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone(),
                client.getCpf(),
                client.getRg(),
                client.getReservation().stream().map(ClientConverter::toReservationResponseDTO).collect(Collectors.toList()),
                client.getRooms().stream().map(ClientConverter::toRoomsResponseDTO).collect(Collectors.toList()),
                toAddressDTO(client.getAddress())
        );
    }

    private static ReservationResponseDTO toReservationResponseDTO(Reservation reservation){
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                reservation.getRoom().getId(),
                reservation.getClient().getId()
        );
    }

    private static RoomsResponseDTO toRoomsResponseDTO(Rooms rooms){
        return new RoomsResponseDTO(
                rooms.getId(),
                rooms.getRoomsStatus(),
                rooms.getFloorNumber(),
                rooms.getRoomsNumber(),
                rooms.getRoomsLevel(),
                rooms.getRoomsServices(),
                rooms.getBedsInRoomNumber()
        );
    }

    private static AddressDTO toAddressDTO(Address address){
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry()
        );
    }


}
