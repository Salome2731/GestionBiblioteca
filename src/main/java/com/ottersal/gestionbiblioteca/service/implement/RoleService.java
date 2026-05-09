package com.ottersal.gestionbiblioteca.service.implement;


import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.ottersal.gestionbiblioteca.model.Role;
import com.ottersal.gestionbiblioteca.repository.RoleRepository;
import com.ottersal.gestionbiblioteca.service.abstracts.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new IllegalArgumentException("El rol ya existe");
        }
        return roleRepository.save(role);
    }

    @Override
    public Role update(UUID id, Role role) {
        Role updateRol = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El rol no existe"));
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El rol no existe"));
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }
}
