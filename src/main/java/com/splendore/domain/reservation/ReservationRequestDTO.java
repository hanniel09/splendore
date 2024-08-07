package com.splendore.domain.reservation;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationRequestDTO(
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate,
        Long roomId,
        UUID clientId) {
}
