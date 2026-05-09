package com.ottersal.gestionbiblioteca.service;

import com.ottersal.gestionbiblioteca.exception.BadRequestException;
import com.ottersal.gestionbiblioteca.model.BibliographicMaterial;
import com.ottersal.gestionbiblioteca.repository.MaterialRepository;
import com.ottersal.gestionbiblioteca.service.impl.MaterialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MaterialServiceImplTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialServiceImpl materialService;

    private BibliographicMaterial material;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        material = new BibliographicMaterial(
                1L,
                "Cien años de soledad",
                "Gabriel García Márquez",
                "Libro",
                "Editorial Sudamericana",
                "978-3-16-148410-0",
                10,
                10,
                "DISPONIBLE",
                "1234567890"
        );
    }

    @Test
    void testSaveMaterial_Success() {
        when(materialRepository.save(any(BibliographicMaterial.class))).thenReturn(material);

        BibliographicMaterial saved = materialService.saveMaterial(material);

        assertNotNull(saved);
        assertEquals("Cien años de soledad", saved.getTitle());
        assertEquals("DISPONIBLE", saved.getStatus());
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    void testUpdateAvailableQuantity_InsufficientStock() {
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        assertThrows(BadRequestException.class, () -> {
            materialService.updateAvailableQuantity(1L, -11);
        });

        verify(materialRepository, never()).save(any(BibliographicMaterial.class));
    }

    @Test
    void testUpdateAvailableQuantity_SuccessAndStatusChange() {
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(materialRepository.save(any(BibliographicMaterial.class))).thenReturn(material);

        BibliographicMaterial updated = materialService.updateAvailableQuantity(1L, -10);

        assertEquals(0, updated.getAvailableQuantity());
        assertEquals("NO_DISPONIBLE", updated.getStatus());
        verify(materialRepository, times(1)).save(material);
    }
}