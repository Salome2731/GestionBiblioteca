package com.ottersal.gestionbiblioteca.service.impl;

import com.ottersal.gestionbiblioteca.exception.BadRequestException;
import com.ottersal.gestionbiblioteca.exception.ResourceNotFoundException;
import com.ottersal.gestionbiblioteca.model.BibliographicMaterial;
import com.ottersal.gestionbiblioteca.repository.MaterialRepository;
import com.ottersal.gestionbiblioteca.service.IMaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaterialServiceImpl implements IMaterialService {

    private final MaterialRepository materialRepository;

    // Inyección por constructor (Buena práctica recomendada)
    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    @Transactional
    public BibliographicMaterial saveMaterial(BibliographicMaterial material) {
        // Validación de negocio: No guardar materiales con cantidades negativas
        if (material.getTotalQuantity() < 0) {
            throw new BadRequestException("La cantidad total no puede ser negativa.");
        }
        
        // Al registrarlo por primera vez, la cantidad disponible es igual al total
        material.setAvailableQuantity(material.getTotalQuantity());
        
        // Ajustar estado automáticamente
        if (material.getAvailableQuantity() > 0) {
            material.setStatus("DISPONIBLE");
        } else {
            material.setStatus("NO_DISPONIBLE");
        }

        return materialRepository.save(material);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BibliographicMaterial> listAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BibliographicMaterial findMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material bibliográfico no encontrado con el ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public BibliographicMaterial findMaterialByCodigoBarras(String codigoBarras) {
        return materialRepository.findByBarCode(codigoBarras)
                .orElseThrow(() -> new ResourceNotFoundException("Material no encontrado con el código de barras: " + codigoBarras ));
    }

    @Override
    @Transactional
    public BibliographicMaterial updateMaterial(Long id, BibliographicMaterial updatedMaterial) {
        BibliographicMaterial existingMaterial = findMaterialById(id);

        existingMaterial.setTitle(updatedMaterial.getTitle());
        existingMaterial.setAuthor(updatedMaterial.getAuthor());
        existingMaterial.setMaterialType(updatedMaterial.getMaterialType());
        existingMaterial.setEditorial(updatedMaterial.getEditorial());
        existingMaterial.setIsbn(updatedMaterial.getIsbn());
        existingMaterial.setTotalQuantity(updatedMaterial.getTotalQuantity());
        existingMaterial.setBarCode(updatedMaterial.getBarCode());

        // Aseguramos que la cantidad disponible nunca supere al total
        if (existingMaterial.getAvailableQuantity() > existingMaterial.getTotalQuantity()) {
            existingMaterial.setAvailableQuantity(existingMaterial.getTotalQuantity());
        }

        return materialRepository.save(existingMaterial);
    }

    @Override
    @Transactional
    public void deleteMaterial(Long id) {
        BibliographicMaterial material = findMaterialById(id);
        materialRepository.delete(material);
    }

    @Override
    @Transactional
    public BibliographicMaterial updateAvailableQuantity(Long id, int quantityChange) {
        BibliographicMaterial material = findMaterialById(id);
        int newQuantity = material.getAvailableQuantity() + quantityChange;

        // Regla de Negocio: No se puede prestar si no hay stock
        if (newQuantity < 0) {
            throw new BadRequestException("No hay suficientes unidades disponibles de este material para realizar el préstamo.");
        }

        // Regla de Negocio: No se pueden registrar más disponibles que el total de existencias
        if (newQuantity > material.getTotalQuantity()) {
            throw new BadRequestException("La cantidad disponible no puede exceder la cantidad total del inventario.");
        }

        material.setAvailableQuantity(newQuantity);

        // Actualización dinámica del estado
        if (newQuantity == 0) {
            material.setStatus("NO_DISPONIBLE");
        } else {
            material.setStatus("DISPONIBLE");
        }

        return materialRepository.save(material);
    }
}