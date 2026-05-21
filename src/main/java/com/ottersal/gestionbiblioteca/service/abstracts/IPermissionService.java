package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.model.Permission;

import java.util.List;
import java.util.UUID;

public interface IPermissionService {
    Permission create(Permission permission);
    boolean delete(UUID id);
    Permission update(UUID id, Permission permission);
    Permission getById(UUID id);
    List<Permission> getAll();
}
