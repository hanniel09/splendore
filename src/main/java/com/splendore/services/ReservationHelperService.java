package com.splendore.services;

import com.splendore.domain.reservation.Reservation;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationHelperService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservationsByIds(List<UUID> reservationIds) {
        return reservationIds.stream()
                .map(reservationRepository::findById)
                .map(optionalReservation -> optionalReservation.orElseThrow(() -> new NotFoundException("Reservation not found")))
                .collect(Collectors.toList());
    }
}
