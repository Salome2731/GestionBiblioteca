package com.ottersal.gestionbiblioteca.repository;

import com.ottersal.gestionbiblioteca.model.Client;
import com.ottersal.gestionbiblioteca.utilities.Conexion;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ClientRepository {

    private final ClientRepositoryHelper helper;

    public ClientRepository(ClientRepositoryHelper helper) {
        this.helper = helper;
    }

    // LISTAMOS A TODOS LOS CLIENTES (GET)
    public List<Client> getClients() {
        List<Client> result = new ArrayList<>();
        Conexion conexion = new Conexion();
        // El try-with-resources cierra la conexión automáticamente (Manejo Manual eficiente)
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(helper.listClients());
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Client client = new Client();
                client.setId(UUID.fromString(rs.getString(1)));
                client.setFirstName(rs.getString(2));
                client.setLastName(rs.getString(3));
                client.setDni(rs.getString(4));
                client.setEmail(rs.getString(5));
                client.setPhone(rs.getString(6));
                client.setStatus(rs.getString(7));
                result.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // INSERTAR CLIENTE (POST)
    public Client saveClient(Client client) {
        if (client.getId() == null) {
            client.setId(UUID.randomUUID()); // Genera un ID si no tiene uno
        }
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(helper.insertClient())) {
            
            ps.setString(1, client.getId().toString());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getDni());
            ps.setString(5, client.getEmail());
            ps.setString(6, client.getPhone());
            ps.setString(7, client.getStatus());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return client;
    }

    // ACTUALIZAR CLIENTE (PUT)
    public Client updateClient(Client client){
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(helper.updateClient())) {
            
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getDni());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getPhone());
            ps.setString(6, client.getStatus());
            ps.setString(7, client.getId().toString());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return client;
    }

    // ELIMINAR CLIENTE (DELETE)
    public boolean deleteClient(UUID id) {
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(helper.deleteClient())) {
            
            ps.setString(1, id.toString());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
