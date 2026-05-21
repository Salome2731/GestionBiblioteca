package com.ottersal.gestionbiblioteca.dtos.response;

import com.ottersal.gestionbiblioteca.model.enums.StatusReservationEnum;

import java.time.LocalDate;
import java.util.UUID;

public record ReservationResponse(
        UUID id,
        LocalDate reservationDate,
        LocalDate expirationDate,
        StatusReservationEnum status,
        UserResponse userDto
) {
}
