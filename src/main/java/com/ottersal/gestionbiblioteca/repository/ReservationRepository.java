package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}
