package com.ottersal.gestionbiblioteca.controller;


import com.ottersal.gestionbiblioteca.model.Role;
import com.ottersal.gestionbiblioteca.model.RoleRequest;
import com.ottersal.gestionbiblioteca.service.abstracts.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


//controlador REST para la gestión de roles dentro del sistema
@Tag(name = "Roles", description = "Endpoints para gestionar los roles de los usuarios")
@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final IRoleService RoleService;

    public RolController(IRoleService RoleService) {
        this.RoleService = RoleService;
    }


    //Endpoint para crear un nuevo rol
    @Operation(
            summary = "Crear un nuevo rol",
            description = "Permite crear un nuevo rol en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<Role>  create(@Valid @RequestBody RoleRequest role){
        return new ResponseEntity<>(RoleService.create(role), HttpStatus.CREATED);
    }

    //Enpoint para obtener todos los roles
    @Operation(
            summary = "Obtener todos los roles",
            description = "Permite obtener una lista de todos los roles disponibles en el sistema"
    )
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Roles obtenidos exitosamente")
    })

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return new ResponseEntity<>(RoleService.getAll(), HttpStatus.OK);
    }


    //Endpoint para obtener un rol por su ID
    @Operation(
            summary = "Obtener rol por ID",
            description = "Busca un rol específico mediante su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable UUID id){
        return new ResponseEntity<>(RoleService.getById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar rol",
            description = "Permite actualizar un rol existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(
            @PathVariable UUID id,
            @Valid @RequestBody RoleRequest role
    ) {
        return new ResponseEntity<>(RoleService.update(id, role), HttpStatus.OK);
    }

    @Operation(
            summary = "Eliminar rol",
            description = "Elimina un rol del sistema mediante su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        RoleService.delete(id);
        return new ResponseEntity<>("Rol eliminado correctamente", HttpStatus.OK);
    }




}
