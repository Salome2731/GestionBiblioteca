package com.ottersal.gestionbiblioteca.controller;


import com.ottersal.gestionbiblioteca.model.Permission;
import com.ottersal.gestionbiblioteca.service.abstracts.IPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Permission", description = "Endpoints para gestionar los permisos de los usuarios")
@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final IPermissionService permissionService;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;


    @Operation(
            summary = "Crear un permiso",
            description = "Permite crear un nuevo permiso en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permiso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PostMapping
    public ResponseEntity<Permission> create(@Valid @RequestBody Permission permission){
        return new ResponseEntity<>(permissionService.create(permission), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Eliminar un permiso",
            description = "Elimina un permiso existente por su UUID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permiso eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Permiso no encontrado"),
            @ApiResponse(responseCode = "400",description = "UUID inválido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listar permisos",
            description = "Obtiene todos los permisos registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado")
    })

    @GetMapping
    public ResponseEntity<List<Permission>> getAll(){
        return new ResponseEntity<>(permissionService.getAll(),HttpStatus.OK);
    }


    @Operation(
            summary = "Buscar permiso por ID",
            description = "Obtiene un permiso específico usando su UUID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso encontrado"),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado"),
            @ApiResponse(responseCode = "400", description = "UUID inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(permissionService.getById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar permiso",
            description = "Actualiza la información de un permiso existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permiso actualizado"),
            @ApiResponse(responseCode = "404", description = "Permiso no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Permission> update(
            @PathVariable UUID id,
            @Valid @RequestBody Permission permission
    ) {
        return new ResponseEntity<>(permissionService.update(id, permission), HttpStatus.OK);
    }




}
