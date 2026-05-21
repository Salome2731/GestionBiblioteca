package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.dtos.request.UserRequest;
import com.ottersal.gestionbiblioteca.dtos.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserService {
    UserResponse create(UserRequest userDto);
    UserResponse findById(UUID id);
    void delete(UUID id);
    UserResponse update(UUID id, UserRequest request);
    Page<UserResponse> findAll(Pageable pageable);
}
