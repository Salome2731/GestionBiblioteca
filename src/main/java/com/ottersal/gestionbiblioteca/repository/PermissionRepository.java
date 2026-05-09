package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    boolean existsPermissionByName(String name);

    @Query("SELECT p FROM Permission p WHERE p.module = :module")
    List<Permission> findByModule(@Param("module") String module);
}
