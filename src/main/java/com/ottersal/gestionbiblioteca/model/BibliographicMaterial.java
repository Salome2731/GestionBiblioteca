package com.ottersal.gestionbiblioteca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "bibliographic_material")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BibliographicMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(name = "material_type", nullable = false, length = 50)
    private String materialType; // Ejemplo: Libro, revista, tesis, material digital

    @Column(length = 100)
    private String editorial;

    @Column(length = 20)
    private String isbn;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false, length = 20)
    private String status; // Ejemplo: disponible, no disponible, reservado

    @Column(name = "codigo_barras", nullable = false, unique = true, length = 50)
    private String codigoBarras; // Campo requerido por el caso de estudio
}