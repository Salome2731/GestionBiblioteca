package com.ottersal.gestionbiblioteca.service.Test;

import com.ottersal.gestionbiblioteca.core.Mapper;
import com.ottersal.gestionbiblioteca.dtos.request.CreateUserRequest;
import com.ottersal.gestionbiblioteca.dtos.response.CreateUserResponse;
import com.ottersal.gestionbiblioteca.model.User;
import com.ottersal.gestionbiblioteca.repository.UserRepository;
import com.ottersal.gestionbiblioteca.service.implement.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    Mapper mapper;
    @InjectMocks
    UserService userService;
    private CreateUserRequest request;
    private User user;
    private CreateUserResponse response;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();

        request = new CreateUserRequest(
                "pass123",
                "Juan",
                "Perez",
                "12345678",
                "3001234567",
                "juan@email.com"
        );

        user = new User();
        user.setId(userId);
        user.setDNI("12345678");
        user.setEmail("juan@email.com");

        response = new CreateUserResponse(
                userId,
                "Juan",
                "Perez",
                "12345678",
                "3001234567",
                "juan@email.com"
        );
    }


    @Test
    void create_whenValidData_shouldReturnResponse() {
        when(userRepository.existsByDNI("12345678")).thenReturn(false);
        when(userRepository.existsByEmail("juan@email.com")).thenReturn(false);
        when(mapper.toUser(request)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(response);

        CreateUserResponse result = userService.create(request);

        assertNotNull(result);
        assertEquals("juan@email.com", result.email());
        verify(userRepository).save(user);
    }

    @Test
    void create_whenDNIAlreadyExists_shouldThrowException() {
        when(userRepository.existsByDNI("12345678")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userService.create(request)
        );

        assertEquals("Usuario con este DNI ya existe", ex.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void create_whenEmailAlreadyExists_shouldThrowException() {
        when(userRepository.existsByDNI("12345678")).thenReturn(false);
        when(userRepository.existsByEmail("juan@email.com")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                userService.create(request)
        );

        assertEquals("Usuario con este email ya existe", ex.getMessage());
        verify(userRepository, never()).save(any());
    }


    @Test
    void delete_whenUserExists_shouldDeleteUser() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.delete(userId);

        verify(userRepository).delete(user);
    }

}