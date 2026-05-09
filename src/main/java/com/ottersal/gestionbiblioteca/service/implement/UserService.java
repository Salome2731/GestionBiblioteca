package com.ottersal.gestionbiblioteca.service.implement;

import com.ottersal.gestionbiblioteca.core.Mapper;
import com.ottersal.gestionbiblioteca.dtos.request.CreateUserRequest;
import com.ottersal.gestionbiblioteca.dtos.response.CreateUserResponse;
import com.ottersal.gestionbiblioteca.model.User;
import com.ottersal.gestionbiblioteca.repository.UserRepository;
import com.ottersal.gestionbiblioteca.service.abstracts.IUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserService(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public CreateUserResponse create(CreateUserRequest userDto) {
        if (userRepository.existsByDNI(userDto.DNI())) {
            // aplicar manejo de excepciones personalizadas
            throw new RuntimeException("Usuario con este DNI ya existe");
        }
        if (userRepository.existsByEmail(userDto.email())) {
            throw new RuntimeException("Usuario con este email ya existe");
        }

        User userCreated = mapper.toUser(userDto);

        userRepository.save(userCreated);
        return mapper.toDto(userCreated);
    }


    @Override
    public CreateUserResponse findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapper.toDto(user);

    }

    @Override
    public void delete(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        userRepository.delete(user);

    }

}
