package com.splendore.services;

import com.splendore.domain.reservation.Reservation;
import com.splendore.domain.reservation.ReservationRequestDTO;
import com.splendore.domain.reservation.ReservationResponseDTO;
import com.splendore.domain.rooms.RoomsResponseDTO;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoomsService roomsService;

    public List<ReservationResponseDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream()
                .map(
                reservation -> new ReservationResponseDTO(
                        reservation.getId(),
                        reservation.getCheckInDate(),
                        reservation.getCheckOutDate(),
                        reservation.getRoom().getId(),
                        reservation.getClient().getId()
                ))
                .collect(Collectors.toList());
    }

    public Reservation getReservationById(UUID id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation not found with id" + id)
        );
    }

    public List<Reservation> getReservationsByIds(List<UUID> reservationIds) {
        return reservationIds.stream()
                .map(reservationRepository::findById)
                .map(optionalReservation -> optionalReservation.orElseThrow(() -> new NotFoundException("Reservation not found")))
                .collect(Collectors.toList());
    }

    public Reservation createReservation(ReservationRequestDTO data) {
        Reservation reservation = new Reservation();
        reservation.setClient(clientService.getClientById(data.clientId()));
        reservation.setRoom(roomsService.getRoomById(data.roomId()));
        reservation.setCheckInDate(data.checkInDate());
        reservation.setCheckOutDate(data.checkOutDate());
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(UUID id, ReservationRequestDTO data) {
        Reservation reservation = this.getReservationById(id);
        reservation.setCheckInDate(data.checkInDate());
        reservation.setCheckOutDate(data.checkOutDate());
        reservation.setRoom(roomsService.getRoomById(data.roomId()));
        reservation.setClient(clientService.getClientById(data.clientId()));
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(UUID id) {
        this.reservationRepository.deleteById(id);
    }
}
