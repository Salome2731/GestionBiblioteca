package com.ottersal.gestionbiblioteca.service;

import com.ottersal.gestionbiblioteca.model.Client;
import com.ottersal.gestionbiblioteca.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.getClients();
    }

    public Client getClientById(UUID id) {
        return clientRepository.getClientById(id);
    }

    public Client createClient(Client client) {
        if (client.getId() == null) {
            client.setId(UUID.randomUUID());
        }
        return clientRepository.insertClient(client);
    }

    
    public Client updateClient(UUID id, Client client) {
        client.setId(id);
        return clientRepository.updateClient(client);
    }

    public boolean deleteClient(UUID id) {
        return clientRepository.deleteClient(id);
    }
    }