package com.ottersal.gestionbiblioteca.service.implement;


import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.ottersal.gestionbiblioteca.model.Permission;
import com.ottersal.gestionbiblioteca.model.Role;
import com.ottersal.gestionbiblioteca.model.RoleRequest;
import com.ottersal.gestionbiblioteca.repository.PermissionRepository;
import com.ottersal.gestionbiblioteca.repository.RoleRepository;
import com.ottersal.gestionbiblioteca.service.abstracts.IRoleService;
import jakarta.persistence.SecondaryTable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Role create(RoleRequest request) {
        roleRepository.findByName(request.name()).ifPresent(role -> {
            throw new IllegalArgumentException("El rol con este nombre ya existe");
        });

        Role role = new Role();
        role.setDescription(request.description());
        role.setName(request.name());

        List<Permission> permissions = permissionRepository.findAllById(request.permissionsIds());
        role.setPermissions(new HashSet<>(permissions));

        return roleRepository.save(role);
    }

    @Override
    public Role update(UUID id, RoleRequest request) {
        Role updateRol = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El rol no existe"));

        updateRol.setName(request.name());
        updateRol.setDescription(request.description());
        List<Permission> permissions = permissionRepository.findAllById(request.permissionsIds());
        updateRol.setPermissions(new HashSet<>(permissions));

        return roleRepository.save(updateRol);
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
        if (!roleRepository.existsById(id)) {
            throw new IllegalArgumentException("El rol no existe");
        }
        roleRepository.deleteById(id);
        return true;
    }
}
