package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.Role;
import com.ottersal.gestionbiblioteca.model.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByName(@NotNull(message = "El nombre del rol es requerido") RoleEnum name);
}
