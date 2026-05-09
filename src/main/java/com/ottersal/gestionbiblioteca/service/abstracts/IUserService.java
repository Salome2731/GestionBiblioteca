package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.dtos.request.CreateUserRequest;
import com.ottersal.gestionbiblioteca.dtos.response.CreateUserResponse;
import com.ottersal.gestionbiblioteca.model.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    CreateUserResponse create(CreateUserRequest userDto);
    CreateUserResponse findById(UUID id);
    void delete(UUID id);

}
