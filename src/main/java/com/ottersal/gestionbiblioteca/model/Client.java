package com.ottersal.gestionbiblioteca.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {


    private UUID id;

    @Size(min = 2, max = 50)
    @NotBlank(message = "El nombre es requerido")
    private String firtsName;


    @Size(min = 2, max = 50)
    @NotBlank(message = "El apellido es requerido")
    private String lastName;


    @NotBlank(message = "El DNI es requerido")
    private String DNI;

    @NotBlank(message = "El telefono es requerido")
    private String phone;

    @NotBlank(message = "El email es requerido")
    @Email(message = "Email invalido")
    private String email;

    private String status;

}