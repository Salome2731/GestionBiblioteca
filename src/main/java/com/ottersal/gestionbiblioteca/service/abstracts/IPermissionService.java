package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.model.Permission;

import java.util.UUID;

public interface IPermissionService {
    Permission create(Permission permission);
    boolean delete(UUID id);
}
