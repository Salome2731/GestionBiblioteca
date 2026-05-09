package com.ottersal.gestionbiblioteca.controller;

@Tag(name = "Material", description = "API para gestión de materiales")
@Operation(summary = "obtener material")

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ottersal.gestionbiblioteca.model.BibliographicMaterial;
import com.ottersal.gestionbiblioteca.service.IMaterialService;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final IMaterialService materialService;

    // Desacoplado: Inyectamos la Interfaz, no la implementación
    public MaterialController(IMaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<BibliographicMaterial> createMaterial(@RequestBody BibliographicMaterial material) {
        return new ResponseEntity<>(materialService.saveMaterial(material), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BibliographicMaterial>> getAllMaterials() {
        return ResponseEntity.ok(materialService.listAllMaterials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BibliographicMaterial> getMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.findMaterialById(id));
    }

    @GetMapping("/barcode/{codigoBarras}")
    public ResponseEntity<BibliographicMaterial> getMaterialByCodigoBarras(@PathVariable String codigoBarras) {
        return ResponseEntity.ok(materialService.findMaterialByCodigoBarras(codigoBarras));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliographicMaterial> updateMaterial(@PathVariable Long id, @RequestBody BibliographicMaterial material) {
        return ResponseEntity.ok(materialService.updateMaterial(id, material));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint adicional para que Miguel pueda simular reservas o flujos desde su módulo
    @PatchMapping("/{id}/stock")
    public ResponseEntity<BibliographicMaterial> updateStock(@PathVariable Long id, @RequestParam int change) {
        return ResponseEntity.ok(materialService.updateAvailableQuantity(id, change));
    }
}