package com.ottersal.gestionbiblioteca.dtos.response;

import java.util.UUID;

public record CreateUserResponse(
        UUID id,
        String firstName,
        String lastName,
        String DNI,
        String phone,
        String email


) {
}
