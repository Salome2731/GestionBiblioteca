package com.ottersal.gestionbiblioteca.controller;

import com.ottersal.gestionbiblioteca.dtos.request.CreateReservationRequest;
import com.ottersal.gestionbiblioteca.dtos.response.CreateReservationResponse;
import com.ottersal.gestionbiblioteca.service.abstracts.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservaciones", description = "Operaciones para gestionar los préstamos y reservas de libros") //
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    @Operation(summary = "Crear una nueva reservación", description = "Registra un préstamo vinculando un usuario y un material bibliográfico") //
    public ResponseEntity<CreateReservationResponse> create(@Valid @RequestBody CreateReservationRequest reservation){
        return new ResponseEntity<>(reservationService.create(reservation), HttpStatus.CREATED);
    }
}