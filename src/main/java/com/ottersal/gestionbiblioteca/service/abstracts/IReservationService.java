package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.dtos.request.ReservationRequest;
import com.ottersal.gestionbiblioteca.dtos.response.ReservationResponse;

public interface IReservationService {
    ReservationResponse create(ReservationRequest reservation);
}
