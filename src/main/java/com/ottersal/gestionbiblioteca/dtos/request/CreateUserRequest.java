package com.ottersal.gestionbiblioteca.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


public record CreateUserRequest(
            @Size(message = "La contraseña debe tener entre 1 y 20 caracteres", min = 1, max = 20)
            @NotBlank(message = "La contraseña es requerida") String password,
            @Size(message = "El nombre debe de tener entre 1 y 15 caracteres", min = 1, max = 15)
            @NotBlank(message = "El nombre es requerido") String firstName,
            @Size(message = "El apellido debe de tener entre 1 y 15 caracteres", min = 1, max = 15)
            @NotBlank(message = "El apellido es requerido") String lastName,
            @NotBlank(message = "El DNI es requerido") String DNI,
            @NotBlank(message = "El número de celular es requerido") String phone,
            @Email(message = "Email invalido")
            @NotBlank(message = "El email es requerido") String email) implements Serializable{

}

