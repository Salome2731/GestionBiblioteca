package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByDNI(String dni);

    boolean existsByEmail(String email);
}
