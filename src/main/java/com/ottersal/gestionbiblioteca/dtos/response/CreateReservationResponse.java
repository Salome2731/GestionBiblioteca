package com.ottersal.gestionbiblioteca.dtos.response;

import com.ottersal.gestionbiblioteca.model.enums.StatusReservationEnum;

import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationResponse(
        UUID id,
        LocalDate reservationDate,
        LocalDate expirationDate,
        StatusReservationEnum status,
        CreateUserResponse userDto
) {
}
