package com.ottersal.gestionbiblioteca.service.abstracts;

import com.ottersal.gestionbiblioteca.model.Role;

import java.util.List;
import java.util.UUID;

//TODO: Falta desarrollar todos los
// servicios que ofrece menos el servicio de crear
public interface IRoleService {
    Role create(Role role);
    Role update(UUID id, Role role);
    List<Role> getAll();
    Role getById(UUID id);
    boolean delete(UUID id);




}
