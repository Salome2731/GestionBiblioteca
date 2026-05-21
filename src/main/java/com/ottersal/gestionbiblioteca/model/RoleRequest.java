package com.ottersal.gestionbiblioteca.model;

import com.ottersal.gestionbiblioteca.model.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link Role}
 */
public record RoleRequest(@NotNull(message = "El nombre del rol es requerido")
                          String
                          name,

                          @NotNull(message = "La descripción es requerida")
                          String
                          description,

                          @NotNull
                          List<UUID> permissionsIds
                          ) implements Serializable {
}