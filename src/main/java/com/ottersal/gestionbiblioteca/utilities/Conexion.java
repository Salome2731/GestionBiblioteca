package com.ottersal.gestionbiblioteca.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {


    Connection con;


    public Connection obtenerConexion() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_biblioteca", "root", "313621Salo@");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            ex.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        Conexion conection = new Conexion();
        try {
            ResultSet r = conection.obtenerConexion().prepareStatement("select * from Client").executeQuery();
            if (r.next()) {
                System.out.println("id: " + r.getString(1) + " firstName: " + r.getString(2));
                while (r.next()) {
                    System.out.println("DNI: " + r.getString("DNI") + "firstName: " + r.getString("firstName"));
                }
            } else {
                System.out.println("NO HAY DATOS");
            }
        } catch (Exception e) {
            System.out.println("#Excepcion: "+e.getMessage());
        }
    }

}