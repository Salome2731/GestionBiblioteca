package com.ottersal.gestionbiblioteca.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "renovaciones")
@Data
public class Renovacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate nuevaFechaDevolucion;

    @OneToOne
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;
}