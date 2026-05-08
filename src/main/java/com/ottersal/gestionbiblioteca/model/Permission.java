package com.ottersal.gestionbiblioteca.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "La descripcion es requerida")
    private String description;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El modulo es requerido")
    private String module;


}
