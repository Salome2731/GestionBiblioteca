package com.ottersal.gestionbiblioteca.service.implement;

import com.ottersal.gestionbiblioteca.model.Permission;
import com.ottersal.gestionbiblioteca.repository.PermissionRepository;
import com.ottersal.gestionbiblioteca.service.abstracts.IPermissionService;
import org.springframework.stereotype.Service;

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
        return false;
    }
}
