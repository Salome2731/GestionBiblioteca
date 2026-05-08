package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.dtos.request.CreateReservationRequest;
import com.ottersal.gestionbiblioteca.dtos.response.CreateReservationResponse;

import java.util.UUID;

public interface IReservationService {
    CreateReservationResponse create(CreateReservationRequest reservation);
}
