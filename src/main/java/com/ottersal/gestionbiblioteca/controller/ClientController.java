package com.ottersal.gestionbiblioteca.controller;

import com.ottersal.gestionbiblioteca.model.Client;
import com.ottersal.gestionbiblioteca.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Client Management", description = "Endpoints for managing library clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Get all clients")
    public List<Client> getAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a client by ID")
    public ResponseEntity<Client> getById(@PathVariable UUID id) {
        Client client = clientService.getClientById(id);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a new client")
    public ResponseEntity<Client> create(@Valid @RequestBody Client client) {
        Client created = clientService.createClient(client);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing client")
    public ResponseEntity<Client> update(@PathVariable UUID id, @Valid @RequestBody Client client) {
        Client updated = clientService.updateClient(id, client);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean deleted = clientService.deleteClient(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}