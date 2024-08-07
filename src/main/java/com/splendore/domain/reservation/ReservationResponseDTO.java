package com.splendore.domain.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,
        LocalDateTime checkInDate,
        LocalDateTime checkOutDate,
        Long roomId,
        UUID clientId
) {
}
