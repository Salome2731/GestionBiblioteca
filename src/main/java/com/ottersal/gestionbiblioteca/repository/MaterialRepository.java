package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.BibliographicMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<BibliographicMaterial, Long> {
    
    // Método para buscar por código de barras (esencial para cuando Miguel haga los préstamos)
    Optional<BibliographicMaterial> findByCodigoBarras(String codigoBarras);
}