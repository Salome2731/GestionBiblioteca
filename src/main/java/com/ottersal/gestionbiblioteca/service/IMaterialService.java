package com.ottersal.gestionbiblioteca.service;

import java.util.List;

import com.ottersal.gestionbiblioteca.model.BibliographicMaterial;

public interface IMaterialService {
    BibliographicMaterial saveMaterial(BibliographicMaterial material);
    List<BibliographicMaterial> listAllMaterials();
    BibliographicMaterial findMaterialById(Long id);
    BibliographicMaterial findMaterialByCodigoBarras(String codigoBarras);
    BibliographicMaterial updateMaterial(Long id, BibliographicMaterial material);
    void deleteMaterial(Long id);
    
    // Lógica de negocio clave: Actualizar inventario cuando ocurra un préstamo o devolución
    BibliographicMaterial updateAvailableQuantity(Long id, int quantityChange);
}