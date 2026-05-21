package com.ottersal.gestionbiblioteca.service.implement;

import com.ottersal.gestionbiblioteca.model.Permission;
import com.ottersal.gestionbiblioteca.repository.PermissionRepository;
import com.ottersal.gestionbiblioteca.service.abstracts.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionService implements IPermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    @Override
    public Permission create(Permission permission) {
        if (permissionRepository.existsPermissionByName(permission.getName())) {
            throw new IllegalArgumentException("El permiso ya existe");
        }
        return permissionRepository.save(permission);
    }

    @Override
    public boolean delete(UUID id) {
        if (!permissionRepository.existsById(id)){
            throw new IllegalArgumentException("Permiso no encontrado");
        }
        permissionRepository.deleteById(id);
        return true;
    }

    @Override
    public Permission update(UUID id, Permission permission) {
        Permission permission1 = permissionRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Permiso no encontrado"));
        permission1.setName(permission.getName());
        permission1.setModule(permission.getModule());
        permission1.setDescription(permission.getDescription());
        return permissionRepository.save(permission1);
    }

    @Override
    public Permission getById(UUID id) {
        return permissionRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Permiso no encontrado"));
    }

    @Override
    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }
}
