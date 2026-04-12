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

    public List<Client> getClients() {
        List<Client> result = new ArrayList<>();
        Conexion conexion = new Conexion();
        Connection connection = conexion.obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(helper.listClients());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId(UUID.fromString(rs.getString(1)));
                client.setFirstName(rs.getString(2));
                client.setLastName(rs.getString(3));
                client.setDNI(rs.getString(4));
                client.setEmail(rs.getString(5));
                client.setPhone(rs.getString(6));
                client.setStatus((rs.getString(7)));
                result.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (Exception close) {
                close.printStackTrace();
            }
        }
        return result;
    }
    public Client insertClient(Client client) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.obtenerConexion();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(helper.insertClient());

            ps.setString(1, client.getId().toString());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getDNI());
            ps.setString(5, client.getEmail());
            ps.setString(6, client.getPhone());
            ps.setString(7, client.getStatus());

            ps.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            sqlException.printStackTrace();
            client = null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception close) {
                close.printStackTrace();
            }
        }

        return client;
    }

    public Client updateClient(Client client){
        Conexion conexion = new Conexion();
        Connection connection = conexion.obtenerConexion();
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(helper.updateClient());
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getDNI());
            ps.setString(4, client.getEmail());
            ps.setString(5, client.getPhone());
            ps.setString(6, client.getStatus());
            ps.setString(7, String.valueOf(client.getId()));
            ps.execute();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            client = null;
        }finally{
            try {
                connection.close();
                ps.close();
            } catch (Exception close) {
                close.printStackTrace();
            }
        }
        return client;
    }

    public Client getClientById(UUID id) {
        Client result = null;
        Conexion conexion = new Conexion();
        Connection connection = conexion.obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(helper.getClientById());
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                result = new Client();
                result.setId(UUID.fromString(rs.getString(1)));
                result.setFirstName(rs.getString(2));
                result.setLastName(rs.getString(3));
                result.setDNI(rs.getString(4));
                result.setEmail(rs.getString(5));
                result.setPhone(rs.getString(6));
                result.setStatus((rs.getString(7)));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (Exception close) {
                close.printStackTrace();
            }
        }
        return result;
    }

    public boolean deleteClient(UUID id) {
        boolean result = false;
        Conexion conexion = new Conexion();
        Connection connection = conexion.obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(helper.deleteClient());
            ps.setString(1, String.valueOf(id));
            ps.execute();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (Exception close) {
                close.printStackTrace();
            }
        }
        return result;
    }

}





