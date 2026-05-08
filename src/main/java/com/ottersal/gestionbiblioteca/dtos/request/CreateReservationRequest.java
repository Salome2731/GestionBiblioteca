package com.ottersal.gestionbiblioteca.dtos.request;

import com.ottersal.gestionbiblioteca.model.enums.StatusReservationEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationRequest(
        @NotNull(message = "La fecha de reserva es obligatoria")
        @NotNull(message = "La fecha de expiración es obligatoria")
        @Future(message = "La fecha de expiración debe ser futura")
        @NotNull(message = "El estado es obligatorio")

        LocalDate reservationDate,
        LocalDate expirationDate,
        StatusReservationEnum status,
        UUID userId
) {
}
