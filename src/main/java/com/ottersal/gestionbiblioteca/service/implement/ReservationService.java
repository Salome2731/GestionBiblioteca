package com.ottersal.gestionbiblioteca.service.implement;

import com.ottersal.gestionbiblioteca.core.Mapper;
import com.ottersal.gestionbiblioteca.dtos.request.ReservationRequest;
import com.ottersal.gestionbiblioteca.dtos.response.ReservationResponse;
import com.ottersal.gestionbiblioteca.model.Reservation;
import com.ottersal.gestionbiblioteca.model.User;
import com.ottersal.gestionbiblioteca.repository.ReservationRepository;
import com.ottersal.gestionbiblioteca.repository.UserRepository;
import com.ottersal.gestionbiblioteca.service.abstracts.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;


    @Override
    public ReservationResponse create(ReservationRequest reservation){
        User user = userRepository.findById(reservation.userId())
                .orElseThrow(()-> new RuntimeException("Usuario con id=" + reservation.userId()+ "no existe"));




        Reservation reservation1 = mapper.toReservation(reservation);

        reservation1.setUser(user);
        reservationRepository.save(reservation1);

        ReservationResponse dto = mapper.toDto(reservation1);
//        dto.userDto()
        return dto;
    }


}
