package com.ottersal.gestionbiblioteca.controller;

import com.ottersal.gestionbiblioteca.dtos.request.ReservationRequest;
import com.ottersal.gestionbiblioteca.dtos.response.ReservationResponse;
import com.ottersal.gestionbiblioteca.service.abstracts.IReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@Valid @RequestBody ReservationRequest reservation){
        return new ResponseEntity<>(reservationService.create(reservation), HttpStatus.CREATED);
    }
}
