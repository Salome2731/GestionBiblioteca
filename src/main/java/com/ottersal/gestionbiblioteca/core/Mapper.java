package com.ottersal.gestionbiblioteca.core;

import com.ottersal.gestionbiblioteca.dtos.request.ReservationRequest;
import com.ottersal.gestionbiblioteca.dtos.request.UserRequest;
import com.ottersal.gestionbiblioteca.dtos.response.ReservationResponse;
import com.ottersal.gestionbiblioteca.dtos.response.UserResponse;
import com.ottersal.gestionbiblioteca.model.Reservation;
import com.ottersal.gestionbiblioteca.model.Role;
import com.ottersal.gestionbiblioteca.model.RoleRequest;
import com.ottersal.gestionbiblioteca.model.User;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    User toUser(UserRequest request);

    UserResponse toDto(User user);

    @Mapping(target = "user", ignore = true)
    Reservation toReservation(ReservationRequest request);

    @Mapping(target = "userDto", source = "user")
    ReservationResponse toDto(Reservation entity);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
}
