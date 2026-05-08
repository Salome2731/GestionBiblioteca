package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    boolean existsPermissionByName(String name);
}
