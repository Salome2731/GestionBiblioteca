package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.model.Role;
import com.ottersal.gestionbiblioteca.model.RoleRequest;

import java.util.List;
import java.util.UUID;

//TODO: Falta desarrollar todos los
// servicios que ofrece menos el servicio de crear
public interface IRoleService {
    Role create(RoleRequest request);
    Role update(UUID id, RoleRequest request);
    List<Role> getAll();
    Role getById(UUID id);
    boolean delete(UUID id);




}
