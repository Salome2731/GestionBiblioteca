package com.ottersal.gestionbiblioteca.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Getter
@Setter
    
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private UUID id;
    private String title;
    private String author;
    private String type;
    private String editorial;
    private int totalQuantity;
    private int availableQuantity;
    private String status;
}
