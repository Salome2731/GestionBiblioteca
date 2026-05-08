package com.ottersal.gestionbiblioteca.core;

import com.ottersal.gestionbiblioteca.dtos.request.CreateReservationRequest;
import com.ottersal.gestionbiblioteca.dtos.request.CreateUserRequest;
import com.ottersal.gestionbiblioteca.dtos.response.CreateReservationResponse;
import com.ottersal.gestionbiblioteca.dtos.response.CreateUserResponse;
import com.ottersal.gestionbiblioteca.model.Reservation;
import com.ottersal.gestionbiblioteca.model.User;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    User toUser(CreateUserRequest request);

    CreateUserResponse toDto(User user);

    @Mapping(target = "user", ignore = true)
    Reservation toReservation(CreateReservationRequest request);

    @Mapping(target = "userDto", source = "user")
    CreateReservationResponse toDto(Reservation entity);
}
