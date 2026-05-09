package com.ottersal.gestionbiblioteca.model;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {


    private UUID id;

    @Size(min = 2, max = 50)
    @NotBlank(message = "El nombre es requerido")
    private String firstName;


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

    @ManyToMany
    @JoinTable(
            name = "client_role",
            joinColumns = @JoinColumn(name = "client_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles;


}
