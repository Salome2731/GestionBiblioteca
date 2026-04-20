package com.ottersal.gestionbiblioteca.controller;


import com.ottersal.gestionbiblioteca.model.Role;
import com.ottersal.gestionbiblioteca.service.abstracts.IRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final IRoleService RoleService;

    public RolController(IRoleService RoleService) {
        this.RoleService = RoleService;
    }

    @PostMapping
    public ResponseEntity<Role>  create(@Valid @RequestBody Role role){
        return new ResponseEntity<>(RoleService.create(role), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return new ResponseEntity<>(RoleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable UUID id){
        return new ResponseEntity<>(RoleService.getById(id), HttpStatus.OK);
    }




}
