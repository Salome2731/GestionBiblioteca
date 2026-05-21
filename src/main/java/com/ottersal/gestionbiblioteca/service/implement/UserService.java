package com.ottersal.gestionbiblioteca.service.implement;

import com.ottersal.gestionbiblioteca.core.Mapper;
import com.ottersal.gestionbiblioteca.dtos.request.UserRequest;
import com.ottersal.gestionbiblioteca.dtos.response.UserResponse;
import com.ottersal.gestionbiblioteca.model.User;
import com.ottersal.gestionbiblioteca.repository.UserRepository;
import com.ottersal.gestionbiblioteca.service.abstracts.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public UserResponse create(UserRequest userDto) {
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
    public UserResponse findById(UUID id) {
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

    @Override
    public UserResponse update(UUID id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existingUser.setFirstName(request.firstName());
        existingUser.setLastName(request.lastName());
        existingUser.setEmail(request.email());
        existingUser.setDNI(request.DNI());
        existingUser.setPassword(request.password());

        User updatedUser = userRepository.save(existingUser);

        return mapper.toDto(updatedUser);
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(mapper::toDto);
    }
}
